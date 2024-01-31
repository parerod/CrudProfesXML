package com.example.crudprofesxml.modelo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.crudprofesxml.modelo.dao.DaoXML
import com.example.crudprofesxml.modelo.entidades.Alumno
import com.example.crudprofesxml.modelo.entidades.Profesor

private lateinit var profesores : MutableList<Profesor>
private lateinit var alumnos : MutableList<Alumno>

private lateinit var daoXML : DaoXML

class ServiceModelView(application: Application) : AndroidViewModel(application)  {


    fun createService() {
        profesores  = mutableListOf()

        alumnos = mutableListOf()
        daoXML = DaoXML(getApplication(),this)
    }

    fun getProfesAssetsSAX(): MutableList<Profesor> {
        var profes = daoXML.procesarArchivoXMLSAX()
        return profes
    }
    fun getProfesAssetsSimpleXML() : MutableList<Profesor> {
        var profes = daoXML.procesarArchivoAssetsXML()
        return profes
    }
    fun getProfesInternoSimpleXML():MutableList<Profesor>{
        var profes = daoXML.procesarArchivoXMLInterno()
        return profes
    }

    fun addProfe(pro : Profesor){
        daoXML.addProfe(pro)
    }
    fun assetsToArchivoInterno(){
        daoXML.copiarArchivoDesdeAssets()
    }



}