package com.example.crudprofesxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.crudprofesxml.modelo.ServiceModelView
import com.example.crudprofesxml.modelo.dao.DaoXML
import com.example.crudprofesxml.modelo.entidades.Profesor

class MainActivity : AppCompatActivity() {

    private val svm : ServiceModelView = ServiceModelView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var daoXML = DaoXML(applicationContext, svm)

        daoXML.copiarArchivoDesdeAssets()
        daoXML.procesarArchivoAssetsXML()
        Log.d("prueba2", "probando procesado con Simple XML Framework")
        svm.profes.forEach {
            Log.d("prueba2", it.toString())
        }

        svm.alumnos.forEach {
            Log.d("pruebaAlu", it.toString())
        }

        val pepe=Profesor("315","656996389","41015","Antonio",6,true)
        daoXML.addProfe(pepe)
        daoXML.ProcesarArchivoXMLInterno()
        svm.profes.forEach {
            Log.d("prueba2", it.toString())
        }

        daoXML.procesarArchivoXMLSAX()
    }
}