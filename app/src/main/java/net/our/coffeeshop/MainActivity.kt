package net.our.coffeeshop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var ref: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_put_data.setOnClickListener {
            ref?.setValue(text_view.text.toString())
        }

        setFirebaseDB()
    }

    private fun setFirebaseDB() {
        val database = FirebaseDatabase.getInstance()
        ref = database.getReference("message")
        ref?.addValueEventListener(valueEventListener)
    }

    val valueEventListener = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError?) {
            Log.d("MAINACTIVITY", "Failed to read value.", error?.toException());
        }

        override fun onDataChange(dataSnapshot: DataSnapshot?) {
            val value = dataSnapshot?.getValue(String::class.java)
            Log.d("MAINACTIVITY", "Value is: " + value)
        }

    }
}
