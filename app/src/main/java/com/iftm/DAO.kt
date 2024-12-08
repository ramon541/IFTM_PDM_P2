package com.iftm

import android.renderscript.Sampler.Value
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val cafe = gson.fromJson(json, Cafe::class.java)
                        listaCafes.add(cafe)
                    }
                }
                callback(listaCafes)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("ERROR DAO getData", "Erro: $error")
            }
        })
    }

    fun orderByPreco(callback: (ArrayList<Cafe>) -> Unit) {
        val listaCafes = ArrayList<Cafe>()

        this.banco.orderByChild("preco").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gson = Gson()
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val cafe = gson.fromJson(json, Cafe::class.java)
                        listaCafes.add(cafe)
                    }
                }
                callback(listaCafes)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("ERROR DAO getData", "Erro: $error")
            }
        })
    }

    fun orderBySabor(callback: (ArrayList<Cafe>) -> Unit) {
        val listaCafes = ArrayList<Cafe>()

        this.banco.orderByChild("sabor").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gson = Gson()
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val cafe = gson.fromJson(json, Cafe::class.java)
                        listaCafes.add(cafe)
                    }
                }
                callback(listaCafes)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("ERROR DAO getData", "Erro: $error")
            }
        })
    }

    fun orderByAroma(callback: (ArrayList<Cafe>) -> Unit) {
        val listaCafes = ArrayList<Cafe>()

        this.banco.orderByChild("aroma").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gson = Gson()
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val cafe = gson.fromJson(json, Cafe::class.java)
                        listaCafes.add(cafe)
                    }
                }
                callback(listaCafes)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("ERROR DAO orderByAroma", "Erro: $error")
            }
        })
    }

    fun orderByAcidez(callback: (ArrayList<Cafe>) -> Unit) {
        val listaCafes = ArrayList<Cafe>()

        this.banco.orderByChild("acidez").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gson = Gson()
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val cafe = gson.fromJson(json, Cafe::class.java)
                        listaCafes.add(cafe)
                    }
                }
                callback(listaCafes)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("ERROR DAO orderByAcidez", "Erro: $error")
            }
        })
    }

    fun findById(codigo: String, callback: (Cafe?) -> Unit) {
        this.banco.child(codigo).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    val gson = Gson()
                    val json = gson.toJson(snapshot.value)
                    val cafe = gson.fromJson(json, Cafe::class.java)
                    callback(cafe)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("ERROR DAO findById", "Erro: $error")
            }
        })
    }

    fun delete(codigo : String) {
        this.banco.child(codigo).removeValue()
    }
}