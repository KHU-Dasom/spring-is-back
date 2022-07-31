package server

import io.grpc.ServerBuilder
import kotlin_gRPC.KotlinGRPC
import kotlin_gRPC.PersonServiceGrpcKt

fun main() {
    val personService = PersonService()
    val server = ServerBuilder
        .forPort(8080)
        .addService(personService)
        .build()

    Runtime.getRuntime().addShutdownHook(Thread {
        server.shutdown()
        server.awaitTermination()
    })

    server.start()
    server.awaitTermination()
}

class PersonService : PersonServiceGrpcKt.PersonServiceCoroutineImplBase() {
    override suspend fun getPersonWithId(request: KotlinGRPC.PersonId): KotlinGRPC.Person {
        val person = People.list[request.id]!!
        return KotlinGRPC.Person.newBuilder()
            .setPersonId(request)
            .setName(person.first)
            .setAge(person.second)
            .build()
    }
}

object People {
    val list = mapOf(
        1 to Pair("홍길동", 25),
        2 to Pair("강심장", 41),
        3 to Pair("이두근", 33)
    )
}