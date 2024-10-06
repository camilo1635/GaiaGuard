package com.example.gaiaguard.data.source

import com.example.gaiaguard.data.model.ObjectiveItem

class ObjectivesLocalDataSource {

    fun getTasks(): List<ObjectiveItem> {
        return listOf(
            ObjectiveItem(1, "Fin de la pobreza"),
            ObjectiveItem(2, "Hambre cero"),
            ObjectiveItem(3, "Salud y bienestar"),
            ObjectiveItem(4, "Educación de calidad"),
            ObjectiveItem(5, "Igualdad de género"),
            ObjectiveItem(6, "Agua limpia y saneamiento"),
            ObjectiveItem(7, "Energía asequible y no contaminante"),
            ObjectiveItem(8, "Trabajo decente y crecimiento económico"),
            ObjectiveItem(9, "Industria, innovación e infraestructura"),
            ObjectiveItem(10, "Reducción de las desigualdades"),
            ObjectiveItem(11, "Ciudades y comunidades sostenibles"),
            ObjectiveItem(12, "Producción y consumo responsables"),
            ObjectiveItem(13, "Agua por el clima"),
            ObjectiveItem(14, "Vida Submarina"),
            ObjectiveItem(15, "Vida de ecosistemas terrestres"),
            ObjectiveItem(16, "Paz, justicia e instituciones solidas"),
            ObjectiveItem(17, "Alianzas para lograr los objetivos")
        )
    }
}