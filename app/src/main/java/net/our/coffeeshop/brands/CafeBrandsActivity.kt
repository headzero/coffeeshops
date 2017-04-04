package net.our.coffeeshop.brands

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_cafe_brands.*
import net.our.coffeeshop.R
import net.our.coffeeshop.brands.view.BrandAdapter
import net.our.coffeeshop.database.DBManager

class CafeBrandsActivity : AppCompatActivity() {

    var database: DBManager? = null
    var adapter: BrandAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_brands)
        initView()
        initAndLoad()
    }

    private fun initView() {
        adapter = BrandAdapter(itemClickListener)
        brands_list.adapter = adapter

        btn_put_data.setOnClickListener {
            database?.create(text_view.text.toString())
        }
    }

    private fun initAndLoad() {
        database = DBManager("brands", valueEventListener)
    }

    val valueEventListener = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError?) {
            Log.d("MAINACTIVITY", "Failed to read value.", error?.toException());
        }

        override fun onDataChange(dataSnapshot: DataSnapshot?) {
            // 아마도 입력 성공. 다른 이벤트 일 때는?

            if (dataSnapshot == null) return
            text_view.setText("")
            val result = dataSnapshot.children // iterable
            val brands: ArrayList<String> = ArrayList()
            for (item in result) {
                Log.d("MAINACTIVITY", "Value is: ${item.key} = ${item.value}")
                brands.add(item.value as String)
            }
            adapter?.setBrandData(brands)
        }
    }

    val itemClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            val itemPosition = brands_list.getChildLayoutPosition(v)
            val brand = adapter?.getItem(itemPosition)
            Toast.makeText(this@CafeBrandsActivity, brand, Toast.LENGTH_SHORT).show()
        }
    }
}
