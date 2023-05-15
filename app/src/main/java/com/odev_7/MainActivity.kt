package com.odev_7

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.odev_7.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var db: DB
    lateinit var btnDate: Button
    lateinit var btnSave: Button
    lateinit var txtTitle:EditText
    lateinit var txtDetail: EditText
    lateinit var listView:ListView

    var selectDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        btnSave = findViewById(R.id.btnSave)
        btnDate = findViewById(R.id.btnDate)

        txtTitle = binding.editTxtTitle
        txtDetail = binding.editTxtDetail
        listView = binding.listView





    }

    override fun onStart() {
        db = DB(this)
        var adapter1= MyAdapter(this,db.allNote())
        val calender = Calendar.getInstance()
        btnDate.setOnClickListener(View.OnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                    Log.d("i", i.toString()) // yıl
                    Log.d("i2", (i2 + 1).toString()) // ay
                    Log.d("i3", i3.toString()) // gün
                    var ay = "${i2+1}"
                    if ( i2+1 < 10 ) {
                        ay = "0${i2+1}"
                    }
                    selectDate = "$i3.$ay.$i"
                },
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH),
            )
            datePickerDialog.show()
        })

        btnSave.setOnClickListener {
            if ( selectDate != "" ) {
                //var status = db.addNote("Tarih Notu", "Tarih Detay", selectDate)
                var status = db.addNote("${txtTitle.text.toString()}","${txtDetail.text.toString()}",selectDate)
                Log.d("status", status.toString())
                selectDate = ""
                binding.editTxtTitle.setText("")
                binding.editTxtDetail.setText("")
                binding.editTxtTitle.requestFocus()

            }else {
                Toast.makeText(this, "Plase Select Date!", Toast.LENGTH_LONG).show()
            }
            var adapter1= MyAdapter(this,db.allNote())
            listView.adapter=adapter1

        }







        adapter1.notifyDataSetChanged()
        listView.adapter=adapter1
        //adapter1=MyAdapter(this,db.allNote())
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this@MainActivity,DetailActivity::class.java)

            var datas=db.allNote()[i]
            val arr = arrayListOf<String>(datas.nid.toString(),datas.title,datas.detail,datas.date)

            intent.putStringArrayListExtra("data",arr)
            startActivity(intent)
        }





        super.onStart()

    }
}