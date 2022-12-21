package com.example.ui_screens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class APIAdaptor(private var list:ArrayList<DataModelItem>, var context: Context):
    RecyclerView.Adapter<APIAdaptor.ViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.widget_tile,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text= list[position].title
    }

    override fun getItemCount(): Int {
       return list.size
    }
    class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        var text: TextView = view.findViewById(R.id.txt_title)
    }


}