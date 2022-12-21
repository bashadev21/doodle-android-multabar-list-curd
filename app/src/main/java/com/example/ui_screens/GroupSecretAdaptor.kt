package com.example.ui_screens

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_screens.R
import de.hdodenhof.circleimageview.CircleImageView

class GroupSecretAdaptor(var list:ArrayList<User>, var recylerontap:RecyclerOnTap,var mainList: ArrayList<User>):RecyclerView.Adapter<GroupSecretAdaptor.ViewHolder>()  {

    override fun onCreateViewHolder(parent:ViewGroup,ViewType:Int):ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.widget_tile,parent,false)
        return ViewHolder(view)
    }


    @SuppressLint("ResourceAsColor")
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
        var text=view.findViewById<TextView>(R.id.txt_title)
        var tile=view.findViewById<ConstraintLayout>(R.id.item_tile)
    }



}

