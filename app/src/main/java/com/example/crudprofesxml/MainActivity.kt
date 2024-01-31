package com.example.crudprofesxml

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.crudprofesxml.modelo.ServiceModelView
import com.example.crudprofesxml.modelo.entidades.Profesor

class MainActivity : AppCompatActivity() {

    private val svm : ServiceModelView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        svm.createService()

        svm.assetsToArchivoInterno()
        var profes = svm.getProfesAssetsSimpleXML()
        Log.d("prueba2", "probando procesado con Simple XML Framework")
        profes.forEach {
            it.alumnos.alumnos.forEach {
                Log.d("pruebaAlu", it.toString())
            }
            Log.d("prueba2", it.toString())
        }

        val pepe= Profesor("315","656996389","41015","Antonio",6,true)
        svm.addProfe(pepe)
        profes = svm.getProfesInternoSimpleXML()
        profes.forEach {
            Log.d("pruebaInterno", it.toString())
        }

        var profesSax = svm.getProfesAssetsSAX()

        profesSax.forEach {
            Log.d("Saxo",it.toString())
        }


    }
}