package com.iftm.model

class Cafe (
      codigo  : String
    , nome    : String
    , nota    : String
    , aroma   : Int
    , acidez  : Int
    , amargor : Int
    , sabor   : Int
    , preco   : Float
){
    var codigo  : String? = null
    var nome    : String
    var nota    : String
    var aroma   : Int
    var acidez  : Int
    var amargor : Int
    var sabor   : Int
    var preco   : Float

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
}