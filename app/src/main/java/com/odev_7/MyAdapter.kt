package com.odev_7

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyAdapter(private val context: Activity, private val arrayList: List<Note>):ArrayAdapter<Note>(context,R.layout.list_item,arrayList)
 {
     override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
         val inflater : LayoutInflater = LayoutInflater.from(context)
         val view:View =inflater.inflate(R.layout.list_item,null)
         val nid: Int
         val title: TextView = view.findViewById(R.id.txtTitle)
         //val detail: TextView = view.findViewById(R.id.txtDetail)

         title.text=arrayList[position].title.toString()
         //detail.text=arrayList[position].detail.toString()

         mutableListOf<Note>()
         return view
     }

}