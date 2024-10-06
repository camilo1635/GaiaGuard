package com.example.gaiaguard.data.source

import com.example.gaiaguard.data.model.ObjetiveItem

class ObjectivesLocalDataSource {
    fun getTasks() = List(15) { i -> ObjetiveItem(i, "Task # $i") }
    val options = listOf(
        "1. Fin de la pobreza",
        "2. Hambre cero",
        "3. Salud y bienestar",
        "4. Educación de calidad",
        "5. Igualdad de género",
        "6. Agua limpia y saneamiento",
        "7. Enegía asequible y no contaminante",
        "8. Trabajo decente y cremiento económico",
        "9. Industria, innovación e infraestructura",
        "10. Reducción de las desigualdades",
        "11. Ciudades y comunidades sostenibles",
        "12. Producción y consumo responsables",
        "13. Agua por el clima",
        "14. Vida Submarina",
        "15. Vida de ecosistemas",
        "16. Paz, justicia e instituciones solidarias",
        "17. Alianzas para lograr los objetivos")
}
