package com.example.ui_screens


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class FragIndividuals() : Fragment(),RecyclerOnTap {
     var recylerView: RecyclerView? =null
    private lateinit var list:ArrayList<User>
    private lateinit var displayList:ArrayList<User>
    private lateinit var dummyList:ArrayList<User>
    private var mainAdaptor:IndividualsAdaptor?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("oncreate")
        return inflater.inflate(R.layout.fragment_frag_individuals, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("onviewCreated")
        setUI()
        super.onViewCreated(view, savedInstanceState)
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
        }
        if(displayList.isEmpty()){
            (activity as MainActivity).showNodata(true);

        }else{
            (activity as MainActivity).showNodata(false);
        }
    }else{
        displayList.clear()
        displayList.addAll(list)
        activity?.findViewById<TextView>(R.id.txtnoData)?.visibility=View.INVISIBLE
    }
        mainAdaptor?.notifyDataSetChanged()
}

    override fun onItemClickAddtoList(list: ArrayList<User>) {
        (activity as MainActivity).IncrementoCount( list.size.toString())
        println(list.toString()+"Pass Data")
        mainAdaptor?.notifyDataSetChanged()

    }

    override fun onClickCancel() {
        (activity as MainActivity).inviteList.clear()
        for (x in list) {
            x.isSelected=false
        }
        onItemClickAddtoList( (activity as MainActivity).inviteList)
    }

    override fun onStart() {
        println("onstart")
        super.onStart()
    }

    override fun onResume() {
        println("onresume")

        super.onResume()
    }

    override fun onPause() {
        println("onpause")

        super.onPause()
    }

    override fun onStop() {
        println("onstop")

        super.onStop()
    }



    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        println("view state stored")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onDestroyView() {
        println("DestroyView")
        super.onDestroyView()
    }

    private fun setUI(){
        recylerView = view?.findViewById(R.id.rvIndividuals)
        list= ArrayList()
        displayList= ArrayList()
        dummyList= ArrayList()
        //getting InviteList
        val args=this.arguments
        val dummyList=  args?.getParcelableArrayList<User>("inviteList")
        println(dummyList.toString()+"aaaaa")
        //List Items
        list.add(User("Mango",false))
        list.add(User("Apple",false))
        list.add(User("Banana",false))

        displayList.addAll(list)
        recylerView?.setHasFixedSize(true)
        mainAdaptor=IndividualsAdaptor(displayList,this,  (activity as MainActivity).inviteList)
        recylerView?.layoutManager=LinearLayoutManager(context)
        recylerView?.adapter=mainAdaptor


    }

}
