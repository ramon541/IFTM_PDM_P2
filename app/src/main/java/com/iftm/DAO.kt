package com.iftm

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.iftm.model.Cafe
import com.google.gson.Gson

class DAO(banco: DatabaseReference) {
    var banco : DatabaseReference
    init{
        this.banco = banco
    }

    fun saveOrUpdate(cafe: Cafe){
        this.banco.child(cafe.codigo).setValue(cafe)
    }

    fun getData(callback: (ArrayList<Cafe>) -> Unit) {
        val listaCafes = ArrayList<Cafe>()

        this.banco.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gson = Gson()
                    Log.i("TESTE", "------")
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val cafe = gson.fromJson(json, Cafe::class.java)
                        listaCafes.add(cafe)
                        //Log.i("TESTE", "Fazenda: " + fazenda.toString())
                    }
                    Log.i("TESTE", "------")
                    callback(listaCafes)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("ERRO DAO getData", "Erro: $error")
            }
        })
    }

    fun findById(codigo : String) {

    }

    fun delete(codigo : String) {
        this.banco.child(codigo).removeValue()
    }
}