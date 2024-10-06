package com.example.gaiaguard.data.source

import com.example.gaiaguard.data.model.ObjetiveItem

class ObjectivesLocalDataSource {

    fun getTasks(): List<ObjetiveItem> {
        return listOf(
            ObjetiveItem(1, "Fin de la pobreza"),
            ObjetiveItem(2, "Hambre cero"),
            ObjetiveItem(3, "Salud y bienestar"),
            ObjetiveItem(4, "Educación de calidad"),
            ObjetiveItem(5, "Igualdad de género"),
            ObjetiveItem(6, "Agua limpia y saneamiento"),
            ObjetiveItem(7, "Energía asequible y no contaminante"),
            ObjetiveItem(8, "Trabajo decente y crecimiento económico"),
            ObjetiveItem(9, "Industria, innovación e infraestructura"),
            ObjetiveItem(10, "Reducción de las desigualdades"),
            ObjetiveItem(11, "Ciudades y comunidades sostenibles"),
            ObjetiveItem(12, "Producción y consumo responsables"),
            ObjetiveItem(13, "Agua por el clima"),
            ObjetiveItem(14, "Vida Submarina"),
            ObjetiveItem(15, "Vida de ecosistemas terrestres"),
            ObjetiveItem(16, "Paz, justicia e instituciones solidas"),
            ObjetiveItem(17, "Alianzas para lograr los objetivos")
        )
    }
}