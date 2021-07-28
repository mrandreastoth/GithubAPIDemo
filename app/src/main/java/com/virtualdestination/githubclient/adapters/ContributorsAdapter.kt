package com.virtualdestination.githubclient.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virtualdestination.githubclient.R
import com.virtualdestination.githubclient.databinding.ContributorItemBinding
import com.virtualdestination.githubclient.models.Contributor

class ContributorsAdapter(private val mRepoContributors: List<Contributor?>, private val mContext: Context) : RecyclerView.Adapter<ContributorsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ContributorItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContributorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val contributor = mRepoContributors[position]

            binding.textViewName.text = contributor!!.login
            binding.textViewCommits.text = mContext.getString(R.string.commit_counts, contributor.contributions)

            Glide.with(mContext).load(contributor.avatarUrl).centerCrop().placeholder(R.drawable.image_github).into(binding.imageViewAvatar)
        }
    }

    override fun getItemCount(): Int {
        return mRepoContributors.size
    }
}