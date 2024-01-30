package com.example.crudprofesxml.modelo.entidades

import org.simpleframework.xml.Attribute

data class Alumno(
    @field:Attribute(name = "nombre")
    var nombre: String = "",

    @field:Attribute(name = "apellido")
    var apellido: String = "",

    @field:Attribute(name = "edad")
    var edad: String = "",

    var notaMedia : Double = 0.0

) {

    fun calcularMedia(notas : MutableList<Int>) {
        var sumaNota = 0
        notas.forEach {
            sumaNota += it
        }


        notaMedia = sumaNota.toDouble() / notas.size
    }
}