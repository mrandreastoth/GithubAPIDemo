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
import com.virtualdestination.githubclient.interfaces.ApiInterface
import com.virtualdestination.githubclient.interfaces.ContributorsInterface
import com.virtualdestination.githubclient.models.Contributor
import com.virtualdestination.githubclient.utilities.GeneralUtilities


class ContributorsFragment : Fragment(), ContributorsInterface {

    private var fetchRepositories: Button? = null
    private var mRecyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var apiService: ApiInterface? = null

    private val repositoryController: ContributorsController = ContributorsController()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view: View = inflater.inflate(R.layout.fragment_repo, container, false)

        fetchRepositories = view.findViewById(R.id.button_fetch)
        progressBar = view.findViewById(R.id.progressBar)
        mRecyclerView = view.findViewById(R.id.list_contributors)

        if (!GeneralUtilities.isOnline(requireContext())) {
            Toast.makeText(requireContext(), getString(R.string.loading_message), Toast.LENGTH_LONG).show()
        } else {
            apiService = ApiController.client!!.create(ApiInterface::class.java)
        }

        loadContributors()

        return view
    }

    override fun fetchContributors(contributorsList: List<Contributor?>) {
        val adapter = ContributorsAdapter(contributorsList, R.layout.contributor_item, requireContext())
        mRecyclerView!!.layoutManager = LinearLayoutManager(requireContext())
        mRecyclerView!!.adapter = adapter
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
        progressBar!!.visibility = View.VISIBLE
    }

    private fun hideProgressDialog() {
        progressBar!!.visibility = View.INVISIBLE
    }

    private fun showFailureDialog() {
        activity?.runOnUiThread {
            val dialogClickListener: DialogInterface.OnClickListener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> fetchRepositories!!.visibility = View.VISIBLE
                    }
                }
            }
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Error!")
            builder.setMessage(getString(R.string.fetching_contributors_error)).setPositiveButton(getString(R.string.button_ok), dialogClickListener).show()
        }
    }
}