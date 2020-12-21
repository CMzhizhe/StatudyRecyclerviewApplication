package com.wjxls.statudyrecyclerviewapplication

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

class BackRecyclerViewAdapter(activity: Activity,list: List<String>) : RecyclerView.Adapter<BackRecyclerViewAdapter.ViewHolder>() {
    private lateinit var weakReferenceActivity: WeakReference<Activity>;
    private lateinit var mList:List<String>;
    init {
        this.weakReferenceActivity = WeakReference<Activity>(activity);
        this.mList = list;
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView = itemView.findViewById<TextView>(R.id.tv_item_back_recyclerview);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = weakReferenceActivity.get()?.layoutInflater?.inflate(R.layout.item_back_recyclerview, parent, false);
        var viewHolder = ViewHolder(view!!);
        return viewHolder;
    }

    override fun getItemCount(): Int {
        return mList?.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var stringValue = mList?.get(position);
        holder.textView.setText(stringValue);
    }
}