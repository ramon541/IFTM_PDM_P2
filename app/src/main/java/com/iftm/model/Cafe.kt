package com.iftm.model

class Cafe (
      codigo  : String
    , nome    : String
    , nota    : String
    , aroma   : Int
    , acidez  : Int
    , amargor : Int
    , sabor   : Int
    , preco   : Double
){
    var codigo  : String
    var nome    : String
    var nota    : String
    var aroma   : Int
    var acidez  : Int
    var amargor : Int
    var sabor   : Int
    var preco   : Double

    init {
        this.codigo  = codigo
        this.nome    = nome
        this.nota    = nota
        this.aroma   = aroma
        this.acidez  = acidez
        this.amargor = amargor
        this.sabor   = sabor
        this.preco   = preco
    }
    override fun toString(): String {
        return "Cafe(codigo='$codigo', nome='$nome', nota='$nota', aroma=$aroma, acidez=$acidez, amargor=$amargor, sabor=$sabor, preco=$preco)"
    }

}