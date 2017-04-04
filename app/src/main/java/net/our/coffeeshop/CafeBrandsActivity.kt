package net.our.coffeeshop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import net.our.coffeeshop.database.DBManager

class CafeBrandsActivity : AppCompatActivity() {

    val database:DBManager by lazy {
        DBManager("brands", valueEventListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_put_data.setOnClickListener {
            database.create(text_view.text.toString())
        }
    }

    val valueEventListener = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError?) {
            Log.d("MAINACTIVITY", "Failed to read value.", error?.toException());
        }

        override fun onDataChange(dataSnapshot: DataSnapshot?) {
            if(dataSnapshot == null) return
            val result = dataSnapshot.children // iterable
            result.forEach {
                Log.d("MAINACTIVITY", "Value is: ${it.key} = ${it.value}")
            }
        }

    }
}
