package com.example.crudprofesxml.modelo.dao

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.crudprofesxml.modelo.ProfesorHandler
import com.example.crudprofesxml.modelo.ServiceModelView
import com.example.crudprofesxml.modelo.entidades.Alumnos
import com.example.crudprofesxml.modelo.entidades.Profesor
import com.example.crudprofesxml.modelo.entidades.Profesores
import org.simpleframework.xml.core.Persister
import java.io.*
import javax.xml.parsers.SAXParserFactory

class DaoXML(private val context: Context, private val svm : ServiceModelView) {

    fun procesarArchivoXMLSAX() : MutableList<Profesor> {

        var profes : MutableList<Profesor> = mutableListOf()

        try {
            val factory = SAXParserFactory.newInstance()
            val parser = factory.newSAXParser()
            val handler = ProfesorHandler()

            val inputStream = context.assets.open("profesores.xml")
            parser.parse(inputStream, handler)

            // Accede a la lista de profesores desde handler.profesores
            handler.profes.forEach {
                Log.d("SAX", "Profes: ${it.nombre}")
                it.alumnos.alumnos.forEach {
                    Log.d("Sale",it.toString())
                }
            }

            profes = handler.profes
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return profes
    }

    fun procesarArchivoAssetsXML(): MutableList<Profesor> {
        var profes : MutableList<Profesor> = mutableListOf()

        val serializer = Persister()
        var inputStream: InputStream? = null
        var reader: InputStreamReader? = null

        try {
            inputStream = context.assets.open("profesores.xml")
            reader = InputStreamReader(inputStream)
            val profesListType = serializer.read(Profesores::class.java, reader, false)
            profes.addAll(profesListType.profes)


        } catch (e: Exception) {
            // Manejo de errores
            e.printStackTrace()
        } finally {
            // Cerrar inputStream y reader
            try {
                reader?.close()
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return profes

    }

    fun addProfe(profesor: Profesor): MutableList<Profesor> {
        var profes : MutableList<Profesor> = procesarArchivoAssetsXML()
        try {
            val serializer = Persister()
            profes.add(profesor)
            val profesList = Profesores(profes)
            val outputStream = context.openFileOutput("profesores.xml", AppCompatActivity.MODE_PRIVATE)
            serializer.write(profesList, outputStream)
            outputStream.close() // Asegúrate de cerrar el outputStream después de escribir
        } catch (e: Exception) {
            e.printStackTrace() // Manejo de errores adecuado
        }
        return profes
    }
    fun copiarArchivoDesdeAssets() {
        val nombreArchivo = "profesores.xml"
        val archivoEnAssets = context.assets.open(nombreArchivo)
        val archivoInterno = context.openFileOutput(nombreArchivo, AppCompatActivity.MODE_PRIVATE)

        archivoEnAssets.copyTo(archivoInterno)
        archivoEnAssets.close()
        archivoInterno.close()
    }

    fun procesarArchivoXMLInterno(): MutableList<Profesor> {
        var profes : MutableList<Profesor> = mutableListOf()
        val nombreArchivo = "profesores.xml"
        val serializer = Persister()

        try {
            // Abrir el archivo para lectura
            val file = File(context.filesDir, nombreArchivo)
            val inputStream = FileInputStream(file)
            val profesList = serializer.read(Profesores::class.java, inputStream)
            profes.addAll(profesList.profes)
            inputStream.close()
        } catch (e: Exception) {

            e.printStackTrace()
        }

        return profes
    }

}