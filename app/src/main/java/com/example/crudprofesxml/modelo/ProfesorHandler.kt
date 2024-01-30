package com.example.crudprofesxml.modelo

import com.example.crudprofesxml.modelo.entidades.Alumno
import com.example.crudprofesxml.modelo.entidades.Profesor
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class ProfesorHandler : DefaultHandler() {

    private val cadena = StringBuilder() //Concatena Cadenas

    var notas = mutableListOf<Int>()

    var profesor : Profesor? = null
    var profes: MutableList<Profesor> = mutableListOf()

    var alumno : Alumno? = null
    var alumnos : MutableList<Alumno> = mutableListOf()

    @Throws(SAXException::class)
    override fun startDocument() {
        cadena.clear()
        profes = mutableListOf()
        println("startDocument")
    }

    @Throws(SAXException::class)
    override fun startElement(uri: String, nombreLocal: String, nombre: String, attributes: Attributes) {
        cadena.setLength(0)
        if (nombre == "profesor") {
            profesor = Profesor()
            //Poner todos los atributos de la entidad del XML
            profesor?.dni = attributes.getValue("nombre")
            profesor?.movil = attributes.getValue("autor")
            profesor?.cp = attributes.getValue("autor")
        }else if (nombre == "alumno") {
            alumno = Alumno()

            alumno?.nombre = attributes.getValue("nombre")
            alumno?.apellido = attributes.getValue("apellido")
            alumno?.edad = attributes.getValue("edad")
        }

        println("startElement: $nombreLocal $nombre")
    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        cadena.append(ch, start, length)
        println("dato final: $cadena")
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String, nombreLocal: String, nombre: String) {
        when (nombre) {
            "nombre" -> profesor?.nombre = cadena.toString()
            "horasLectivas" -> profesor?.horasLectivas = cadena.toString().toInt()
            "mayor55" -> profesor?.mayor55 = cadena.toString().toBoolean()
            "profesor" -> profesor?.let { profes.add(it) }
            "notaSGE" -> notas.add(cadena.toString().toInt())
            "notaHLC" -> notas.add(cadena.toString().toInt())
            "notaPMSM" -> notas.add(cadena.toString().toInt())
            "notaADAT" -> notas.add(cadena.toString().toInt())
            "notaEIE" -> notas.add(cadena.toString().toInt())
            "notaPSP" -> notas.add(cadena.toString().toInt())
            "notaDI" -> notas.add(cadena.toString().toInt())
            "alumno" -> {
                alumno?.calcularMedia(notas)
                alumno?.let { alumnos.add(it) }
            }
        }



        println("endElement: $nombreLocal $nombre")
    }

    @Throws(SAXException::class)
    override fun endDocument() {
        println("endDocument")
    }


}