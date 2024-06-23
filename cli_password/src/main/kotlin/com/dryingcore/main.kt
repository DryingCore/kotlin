package com.dryingcore

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import org.apache.commons.lang3.RandomStringUtils

fun main() {
    val connectionString = System.getenv("MONGODB_URI")
        ?: throw IllegalArgumentException("MONGODB_URI environment variable not set")

    val settings = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString(connectionString))
        .build()
    val mongoClient = MongoClients.create(settings)
    val database: MongoDatabase = mongoClient.getDatabase("passwordGenerator")
    val collection: MongoCollection<Document> = database.getCollection("passwords")

    val senhaGerada = gerarSenhaAleatoria(12)
    salvarSenha(collection, senhaGerada)

    println("Senha gerada e salva no MongoDB: $senhaGerada")

    mongoClient.close()
}

fun gerarSenhaAleatoria(tamanho: Int): String {
    return RandomStringUtils.randomAlphanumeric(tamanho)
}

fun salvarSenha(collection: MongoCollection<Document>, senha: String) {
    val documento = Document("senha", senha)
    collection.insertOne(documento)
}
