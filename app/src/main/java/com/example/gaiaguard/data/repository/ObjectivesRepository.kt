package com.example.gaiaguard.data.repository

import com.example.gaiaguard.data.model.Item
import com.example.gaiaguard.data.model.ObjectiveItem
import com.example.gaiaguard.data.source.ObjectivesLocalDataSource
import com.example.gaiaguard.data.source.ObjectivesRemoteDataSource
import kotlinx.coroutines.flow.Flow

class ObjectivesRepository (
    private val objectivesLocalDataSource: ObjectivesLocalDataSource,
    private val objectivesRemoteDataSource: ObjectivesRemoteDataSource
){

    suspend fun getObjectives(): Flow<List<ObjectiveItem>> = objectivesRemoteDataSource.getObjectives()

    suspend fun getItemsFromObjective(objectiveId: String, level: Int): Flow<List<Item>> = objectivesRemoteDataSource.getItemsFromObjective(objectiveId, level)
}