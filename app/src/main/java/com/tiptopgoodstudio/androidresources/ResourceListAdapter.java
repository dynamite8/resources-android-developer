package com.tiptopgoodstudio.androidresources;

/**
 * Created by Divya on 3/3/2018.
 * For GrowWithGoogleChallengeScholarship Women In Technology Learning Project
 * Android Dev Resources
 *
 * This class creates the adapter for the items in the Recycler View
 *
 * The following added by Divya on 3/24/2018
 * Changed the String array to List<Resource> to hold the mock Resource items
 * Updated the onBindViewHolder method to display all data from the Resource item list
 * Added an onClickListener to the resource item
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiptopgoodstudio.androidresources.db.entity.Resource;

import java.util.List;

public class ResourceListAdapter
        extends RecyclerView.Adapter<ResourceListAdapter.ResourceListAdapterViewHolder>{

    // A list of com.tiptopgoodstudio.androidresources.db.entity.Resource items
    // Currently generated by mock data
    private List<Resource> mResourceData;

    final private ResourceClickListener mOnClickListener;

    public ResourceListAdapter(ResourceClickListener listener) {
        mOnClickListener = listener;
    }

    public interface ResourceClickListener {
        void onResourceItemClick(String url);
    }

    @Override
    public ResourceListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.resource_item, parent, false);
        ResourceListAdapterViewHolder viewHolder = new ResourceListAdapterViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResourceListAdapterViewHolder holder, int position) {
        Resource currentResource = mResourceData.get(position);

        holder.itemView.setTag(currentResource.getResourceId());

        holder.mResourceUrl.setText(currentResource.getResourceUrl());

        holder.mResourceDescription.setText(currentResource.getResourceDescription());

    }

    @Override
    public int getItemCount() {
        if(mResourceData == null) {
            return 0;
        }
        return mResourceData.size();
    }

    public void setResourceData(List<Resource> resourceData) {
        mResourceData = resourceData;
        notifyDataSetChanged();
    }

    public class ResourceListAdapterViewHolder extends RecyclerView.ViewHolder
                                                implements View.OnClickListener {

        public final TextView mResourceDescription;
        public final TextView mResourceUrl;

        public ResourceListAdapterViewHolder(View itemView) {
            super(itemView);
            mResourceDescription = (TextView) itemView.findViewById(R.id.tv_resource_description);
            mResourceUrl = (TextView) itemView.findViewById(R.id.tv_resource_url);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            String url = mResourceData.get(clickedPosition).getResourceUrl();
            mOnClickListener.onResourceItemClick(url);
        }
    }

}
