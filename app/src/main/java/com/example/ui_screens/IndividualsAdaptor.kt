package com.example.ui_screens

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class IndividualsAdaptor(var list: ArrayList<User>,var recylerontap: RecyclerOnTap ,var mainList: ArrayList<User>):RecyclerView.Adapter<IndividualsAdaptor.ViewHolder>()   {
    override fun onCreateViewHolder(parent:ViewGroup,ViewType:Int):ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.widget_tile,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder:ViewHolder, position:Int) {
        holder.text.text= list[position].name
         println("calling")
        if(list[position].isSelected){

            holder.tile.setBackgroundColor(Color.parseColor("#FF03DAC5"))
        }else{
            holder.tile.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }

        holder.tile.setOnClickListener(){

        list[position].isSelected = !list[position].isSelected
            if(list[position].isSelected){

                mainList.add(list[position])
            }else{

                mainList.remove(list[position])
            }

            recylerontap.onItemClickAddtoList(mainList)


        }
    }
    override fun getItemCount():Int {
        return list.size
    }

    class ViewHolder(view:View) :RecyclerView.ViewHolder(view){
        var text: TextView =view.findViewById(R.id.txt_title)
        var tile: ConstraintLayout =view.findViewById(R.id.item_tile)
    }



}

