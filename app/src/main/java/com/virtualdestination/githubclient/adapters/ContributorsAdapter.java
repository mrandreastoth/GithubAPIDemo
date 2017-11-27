package com.virtualdestination.githubclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.virtualdestination.githubclient.R;
import com.virtualdestination.githubclient.objects.Contributor;

import java.util.List;

/**
 * Created by Rashid on 26/11/2017.
 */


public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.ContributorsViewHolder> {

    private Context mContext;
    private int rowLayout;
    private List<Contributor> mRepoContributors;


    public static class ContributorsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView commitsTextView;
        ImageView avatarImageView;


        public ContributorsViewHolder(View v) {
            super(v);
            avatarImageView = v.findViewById(R.id.imageView_avatar);
            titleTextView = v.findViewById(R.id.textView_name);
            commitsTextView = v.findViewById(R.id.textView_commits);
        }
    }

    public ContributorsAdapter(List<Contributor> contributors, int rowLayout, Context context) {
        this.mContext = context;
        this.mRepoContributors = contributors;
        this.rowLayout = rowLayout;
    }

    @Override
    public ContributorsAdapter.ContributorsViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ContributorsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ContributorsViewHolder holder, final int position) {

        Contributor contributor = mRepoContributors.get(position);

        holder.titleTextView.setText(contributor.getLogin());
        holder.commitsTextView.setText(mContext.getString(R.string.commit_counts, contributor.getContributions()));
        Picasso.with(mContext).load(contributor.getAvatarUrl()).placeholder(R.drawable.image_github).into(holder.avatarImageView);

    }

    @Override
    public int getItemCount() {
        return mRepoContributors.size();
    }
}

//public class ContributorsAdapter extends BaseAdapter {
//
//    private Context mContext;
//    private LayoutInflater mInflater;
//    private List<Contributor> mRepoContributors;
//
//    public ContributorsAdapter(Context context, List<Contributor> items) {
//        mContext = context;
//        mRepoContributors = items;
//        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//        if(convertView == null) {
//            convertView = mInflater.inflate(R.layout.contributor_item, parent, false);
//            holder = new ViewHolder();
//            holder.avatarImageView = convertView.findViewById(R.id.imageView_avatar);
//            holder.titleTextView = convertView.findViewById(R.id.textView_name);
//            holder.commitsTextView = convertView.findViewById(R.id.textView_commits);
//
//            convertView.setTag(holder);
//        }
//        else{
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//
//        Contributor contributor = ( Contributor) getItem(position);
//
//        holder.titleTextView.setText(contributor.getLogin());
//        holder.commitsTextView.setText(mContext.getString(R.string.commit_counts,  contributor.getContributions()));
//        Picasso.with(mContext).load(contributor.getAvatarUrl()).placeholder(R.drawable.image_github).into(holder.avatarImageView);
//
//        return convertView;
//    }
//
//    @Override
//    public int getCount() {
//        return mRepoContributors.size();
//    }
//
//
//    @Override
//    public Object getItem(int position) {
//        return mRepoContributors.get(position);
//    }
//
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    private static class ViewHolder {
//        TextView titleTextView;
//        TextView commitsTextView;
//        ImageView avatarImageView;
//    }
//}
