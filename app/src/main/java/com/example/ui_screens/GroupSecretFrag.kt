package com.example.ui_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class GroupSecretFrag() : Fragment(),RecyclerOnTap {
    var recylerView: RecyclerView? =null
    private lateinit var list:ArrayList<User>
    private lateinit var displayList:ArrayList<User>
    private lateinit var dummyList:ArrayList<User>
    var mainAdaptor:GroupSecretAdaptor?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        list= ArrayList()
        displayList= ArrayList()
        dummyList= ArrayList()
        //getting InviteList
        val args=this.arguments
        val dummyList=  args?.getParcelableArrayList<User>("inviteList")
        list.add(User("Flutter",false))
        list.add(User("Android",false))
        list.add(User("Kotlin",false))
        displayList.addAll(list)
        val view=inflater.inflate(R.layout.fragment_group_secret, container, false)

        recylerView = view.findViewById(R.id.rcGroupSecret)
        recylerView?.setHasFixedSize(true)
        recylerView?.layoutManager = LinearLayoutManager(context)
        mainAdaptor=GroupSecretAdaptor(displayList,this,  (activity as MainActivity).inviteList)
        recylerView?.adapter =mainAdaptor

        return view
    }
    fun addItem(addText:String){
        if(addText.isNotEmpty()){
            list.add(0,User(addText,true))
            list= list.distinct() as ArrayList<User>
            mainAdaptor?.notifyDataSetChanged()
            activity?.findViewById<EditText>(R.id.search_edit_txt)?.text?.clear()
        }
    }
    fun searchFun(searchTxt: String){
        val search=searchTxt.lowercase()
        if(search.isNotEmpty()){
            displayList.clear()
            list.forEach{
                if (it.name.lowercase(Locale.getDefault()).contains(search)) {
                    displayList.add(it)
                    println(displayList.size.toString() + "Thiss")
                }
                if(displayList.size==0){
                    (activity as MainActivity).showNodata(true);

                }else{
                    (activity as MainActivity).showNodata(false);

                }
            }
        }else{
            displayList.clear()
            displayList.addAll(list)
            activity?.findViewById<TextView>(R.id.txtnoData)?.visibility=View.INVISIBLE
        }
        mainAdaptor?.notifyDataSetChanged()
    }



    override fun onItemClickAddtoList(list: ArrayList<User>) {

        var count= activity?.findViewById<TextView>(R.id.count)
        count?.text = list.size.toString()

        println(list.toString()+"Pass Data")
        mainAdaptor?.notifyDataSetChanged()

    }

    override fun onClickCancel() {
//        inviteList.clear()
        for (x in (activity as MainActivity).inviteList) {
            x.isSelected=false
        }
        onItemClickAddtoList((activity as MainActivity).inviteList)

    }

}