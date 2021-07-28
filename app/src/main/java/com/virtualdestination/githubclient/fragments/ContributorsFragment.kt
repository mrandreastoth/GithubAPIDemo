package com.virtualdestination.githubclient.fragments


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.virtualdestination.githubclient.R
import com.virtualdestination.githubclient.adapters.ContributorsAdapter
import com.virtualdestination.githubclient.apicontrollers.ApiController
import com.virtualdestination.githubclient.apicontrollers.ContributorsController
import com.virtualdestination.githubclient.databinding.FragmentContributorsBinding
import com.virtualdestination.githubclient.interfaces.ApiInterface
import com.virtualdestination.githubclient.interfaces.ContributorsInterface
import com.virtualdestination.githubclient.models.Contributor
import com.virtualdestination.githubclient.utilities.GeneralUtilities


class ContributorsFragment : Fragment(R.layout.fragment_contributors), ContributorsInterface {

    private var apiService: ApiInterface? = null
    private val repositoryController: ContributorsController = ContributorsController()
    private var viewBinding: FragmentContributorsBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentContributorsBinding.bind(view)

        if (!GeneralUtilities.isOnline(requireContext())) {
            Toast.makeText(requireContext(), getString(R.string.loading_message), Toast.LENGTH_LONG).show()
        } else {
            apiService = ApiController.client!!.create(ApiInterface::class.java)
        }

        loadContributors()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun fetchContributors(contributorsList: List<Contributor?>) {
        val adapter = ContributorsAdapter(contributorsList, requireContext())
        viewBinding!!.rvContributors.layoutManager = LinearLayoutManager(requireContext())
        viewBinding!!.rvContributors.adapter = adapter
        hideProgressDialog()
    }

    override fun onError(t: Throwable) {
        t.printStackTrace()
        hideProgressDialog()
        showFailureDialog()
    }

    private fun loadContributors() {
        showProgressDialog()
        repositoryController.fetchRepositoryContributors(this, apiService!!)
    }

    private fun showProgressDialog() {
        viewBinding!!.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressDialog() {
        viewBinding!!.progressBar.visibility = View.INVISIBLE
    }

    private fun showFailureDialog() {
        activity?.runOnUiThread {
            val dialogClickListener: DialogInterface.OnClickListener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> viewBinding!!.btnFetch.visibility = View.VISIBLE
                    }
                }
            }
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Error!")
            builder.setMessage(getString(R.string.fetching_contributors_error)).setPositiveButton(getString(R.string.button_ok), dialogClickListener).show()
        }
    }
}