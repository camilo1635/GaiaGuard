package com.example.gaiaguard.data.repository

import com.example.gaiaguard.data.model.ObjetiveItem
import com.example.gaiaguard.data.source.ObjectivesLocalDataSource

class ObjectivesRepository (
    private val objectivesLocalDataSource: ObjectivesLocalDataSource
){
    fun getTask(): List<ObjetiveItem> {
        return objectivesLocalDataSource.getTasks()
    }

}