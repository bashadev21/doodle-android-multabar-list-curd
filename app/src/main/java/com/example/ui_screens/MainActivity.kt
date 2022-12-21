package com.example.ui_screens
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.doodleandroid.TabAdaptor
import com.google.android.material.tabs.TabLayout
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable


class MainActivity : AppCompatActivity(),Incrementor {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var searchtxt:EditText
    private lateinit var recyleView:RecyclerView
    private lateinit var list:ArrayList<DataModelItem>
    private lateinit var count : TextView


    var inviteList= ArrayList<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
   //     apiCall()
       inviteUI()
    }


private fun apiCall(){
    recyleView=findViewById(R.id.rvApiData)
    list= ArrayList()
    val layoutManager=LinearLayoutManager(this)
    val adaptor=APIAdaptor(list,this)
    recyleView.layoutManager=layoutManager
val moshi=Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val retrofit:Retrofit=Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build()
    val api:APIInterface=retrofit.create(APIInterface::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            //api call
        val response=api.getData()
            if(response.isSuccessful){
                for(myData in response.body()!!){
                    println(myData.toString())
                    list.add(myData)
                }
            }else{
               println( response.message().toString())
            }
           withContext(Dispatchers.Main){
//               delay(5000)//waiting next thread runs
                adaptor.notifyDataSetChanged()
                recyleView.adapter=adaptor

            }


    }

///Normal method/////////////
//    var call:Call<DataModel> =api.getData()
//    call.enqueue(object: Callback<DataModel?>{
//        override fun onResponse(call: Call<DataModel?>, response: Response<DataModel?>) {
//            if(response.isSuccessful){
//                list.clear()
//                for(myData in response.body()!!){
//                    list.add(myData)
//                }
//                adaptor.notifyDataSetChanged()
//                recyleView.adapter=adaptor
//            }
//        }
//
//        override fun onFailure(call: Call<DataModel?>, t: Throwable) {
//            println(t.message.toString()+"ssss")
//        }
//
//    })
}
    private fun inviteUI(){
        tabLayout=findViewById(R.id.tab_layout)
        viewPager=findViewById(R.id.tabs)
        searchtxt=findViewById(R.id.search_edit_txt)
        searchtxt=findViewById(R.id.search_edit_txt)

     count=   findViewById(R.id.count)
        count.text="0"
        val individualFrag=FragIndividuals()
        findViewById<Button>(R.id.btn_invite).setOnClickListener(){

        }
        findViewById<Button>(R.id.btn_cancel).setOnClickListener(){
            println(inviteList.toString()+"ssssss")
            grpSecretFrag.onClickCancel()
            individualFrag.onClickCancel()

        }
        findViewById<ImageView>(R.id.add_btn).setOnClickListener(){
            if(viewPager.currentItem==1){
                grpSecretFrag.addItem(searchtxt.text.toString())
            }else{
                individualFrag.addItem(searchtxt.text.toString())

            }

        }

        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.tIndividuals)).setIcon(R.drawable.ic_baseline_person_24))
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.tGroups)).setIcon(R.drawable.ic_baseline_group_24))
        tabLayout.tabGravity=TabLayout.GRAVITY_FILL
        val adaptor=TabAdaptor(supportFragmentManager,tabLayout.tabCount,individualFrag,lifecycle)
        viewPager.adapter=adaptor
        val bundle=Bundle()
        bundle.putParcelableArrayList("inviteList", inviteList)
        individualFrag.arguments=bundle
        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        searchtxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                println(viewPager.currentItem.toString())
                if(viewPager.currentItem==1){
                    grpSecretFrag.searchFun(s.toString())
                }else{
                    individualFrag.searchFun(s.toString())

                }

                //    println(s.toString())
                //  if (s.length != 0) field2.setText("")
            }
        })

    }

    override fun IncrementoCount(countVal:String) {
        count.text=countVal;
    }

    override fun showNodata(value: Boolean) {
        findViewById<TextView>(R.id.txtnoData)?.visibility = if(value){
            View.VISIBLE
        }else{
            View.INVISIBLE

        }

    }

}

var grpSecretFrag=GroupSecretFrag()
var BASEURL="https://jsonplaceholder.typicode.com/"

data class User(val name: String, var isSelected: Boolean): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}