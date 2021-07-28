package com.virtualdestination.githubclient.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.virtualdestination.githubclient.R
import com.virtualdestination.githubclient.adapters.ContributorsAdapter.ContributorsViewHolder
import com.virtualdestination.githubclient.models.Contributor

class ContributorsAdapter(private val mRepoContributors: List<Contributor?>,
                          private val rowLayout: Int, private val mContext: Context) :
    RecyclerView.Adapter<ContributorsViewHolder>() {
    class ContributorsViewHolder(v: View) : ViewHolder(v) {
        var titleTextView: TextView = v.findViewById(R.id.textView_name)
        var commitsTextView: TextView = v.findViewById(R.id.textView_commits)
        var avatarImageView: ImageView = v.findViewById(R.id.imageView_avatar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return ContributorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContributorsViewHolder, position: Int) {
        val contributor = mRepoContributors[position]
        holder.titleTextView.text = contributor!!.login
        holder.commitsTextView.text = mContext.getString(R.string.commit_counts, contributor.contributions)
        Glide.with(mContext).load(contributor.avatarUrl).centerCrop().placeholder(R.drawable.image_github).into(holder.avatarImageView)
    }

    override fun getItemCount(): Int {
        return mRepoContributors.size
    }
}