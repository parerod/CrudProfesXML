package com.example.crudprofesxml.modelo.entidades

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root



@Root(name = "alumnos")
data class Alumnos(
    @field:ElementList(inline = true, entry = "alumno", required = false)
    var alumnos: MutableList<Alumno> = mutableListOf()
)

@Root(name = "alumno")
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