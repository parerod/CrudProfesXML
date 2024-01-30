package com.example.crudprofesxml.modelo.entidades

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "profesores")
data class Profesores(
    @field:ElementList(inline = true, entry = "profesor")
    var profes: List<Profesor> = mutableListOf()
)

@Root(name = "profesor")
data class Profesor(
    @field:Attribute(name = "dni", required = false)
    var dni: String = "",

    @field:Attribute(name = "movil", required = false)
    var movil: String = "",

    @field:Attribute(name = "cp", required = false)
    var cp: String = "",

    @field:Element(name = "nombre")
    var nombre: String = "",

    @field:Element(name = "horasLectivas")
    var horasLectivas: Int = 0,

    @field:Element(name = "mayor55")
    var mayor55: Boolean = false,
) {


}