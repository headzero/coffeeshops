package net.our.coffeeshop.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DBManager {
    val databaseRef: DatabaseReference

    constructor(referenceKey: String, valueEventListener: ValueEventListener?) {
        // databaseReference를 만드는 시점에 해당 child를 가져오게 되어있음.
        databaseRef = FirebaseDatabase.getInstance().getReference(referenceKey)
        valueEventListener?.let {
            databaseRef.addValueEventListener(valueEventListener)
        }
    }

    open fun create(data: Any) {
        val key = databaseRef.push().key
        databaseRef.child(key).setValue(data)
    }

    open fun update(key: String, data: Any) {
        databaseRef.child(key).setValue(data)
    }

    open fun remove(key: String) {
        databaseRef.child(key).removeValue()
    }

    open fun read(key: String): Any {
        return databaseRef.child(key)
    }
}