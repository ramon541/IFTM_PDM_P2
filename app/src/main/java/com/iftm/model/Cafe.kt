package com.iftm.model

import kotlinx.serialization.Serializable

@Serializable
data class Cafe (
      val codigo  : String
    , val nome    : String
    , val nota    : String
    , val aroma   : Int
    , val acidez  : Int
    , val amargor : Int
    , val sabor   : Int
    , val preco   : Double
){
    override fun toString(): String {
        return "Cafe(codigo='$codigo', nome='$nome', nota='$nota', aroma=$aroma, acidez=$acidez, amargor=$amargor, sabor=$sabor, preco=$preco)"
    }

}