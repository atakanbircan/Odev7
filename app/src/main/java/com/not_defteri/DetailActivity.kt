package com.not_defteri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.not_defteri.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var db:DB
    lateinit var binding: ActivityDetailBinding
    var arr1 = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DB(this)

        intent.getStringArrayListExtra("data")?.let {
            arr1=it

                binding.textTitle.text = "Title : "+it[1]
                binding.textDetail.text = "Detail : "+it[2]

                binding.textDate.text = "Date : "+it[3]

                val nid=it[0].toInt()


                binding.btnDelete.setOnClickListener {
                    db.deleteNote(nid)
                    val intent1 = Intent(this@DetailActivity,MainActivity::class.java)
                    startActivity(intent1)
                }
        }

    }


}