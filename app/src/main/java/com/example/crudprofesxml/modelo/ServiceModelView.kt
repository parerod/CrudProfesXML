package com.example.crudprofesxml.modelo

import androidx.lifecycle.ViewModel
import com.example.crudprofesxml.modelo.entidades.Alumno
import com.example.crudprofesxml.modelo.entidades.Profesor

class ServiceModelView : ViewModel() {

    var profes : MutableList<Profesor> = mutableListOf()

    var alumnos : MutableList<Alumno> = mutableListOf()


}