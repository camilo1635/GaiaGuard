package com.example.gaiaguard.data.source

import android.util.Log
import com.example.gaiaguard.data.Constants
import com.example.gaiaguard.data.model.Item
import com.example.gaiaguard.data.model.ObjectiveItem
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ObjectivesRemoteDataSource(
    private val firestore: FirebaseFirestore
) {

    val TAG = "ObjectivesRemoteDataSource"
    val odsCollectionRef = firestore.collection(Constants.Collection.ODS)

    suspend fun getObjectives(): Flow<List<ObjectiveItem>> = flow {
        val objectiveItems = suspendCancellableCoroutine { continuation ->
            odsCollectionRef.get()
                .addOnSuccessListener { result ->
                    val list = result.documents.map { document ->
                        Log.d(TAG, "getObjectives: ${document.toObject(ObjectiveItem::class.java)}")
                        ObjectiveItem(
                            documentId = document.id, // Agregar el ID del documento
                            numeroODS = document.toObject(ObjectiveItem::class.java)?.numeroODS ?: 1,
                            nombre = document.toObject(ObjectiveItem::class.java)?.nombre ?: "No hay nombre"
                        )
                    }
                    continuation.resume(list)
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error getting documents: ", exception)
                    continuation.resumeWithException(exception)
                }
        }

        Log.d(TAG, "getObjectives: $objectiveItems")
        emit(objectiveItems)
    }

    suspend fun getItemsFromObjective(objectiveId: String): Flow<List<Item>> = flow {
        Log.d(TAG, "getItemsFromObjective: $objectiveId")

        val items = suspendCancellableCoroutine { continuation ->
            odsCollectionRef
                .document(objectiveId)
                .collection(Constants.Collection.ITEMS)
                .get()
                .addOnSuccessListener { result ->
                    val list = result.documents.map { document ->
                        Log.d(TAG, "getObjectives: ${document.toObject(Item::class.java)}")
                        document.toObject(Item::class.java) ?: Item()
                    }
                    continuation.resume(list)
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error getting documents: ", exception)
                    continuation.resumeWithException(exception)
                }
        }

        Log.d(TAG, "getObjectives: $items")
        emit(items)
    }

}