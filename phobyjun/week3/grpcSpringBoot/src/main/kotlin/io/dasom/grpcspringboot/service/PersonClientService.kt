package io.dasom.grpcspringboot.service

import io.dasom.grpcspringboot.dto.PersonDto
import io.grpc.StatusRuntimeException
import kotlin_gRPC.KotlinGRPC.PersonId
import kotlin_gRPC.PersonServiceGrpc.PersonServiceBlockingStub
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service

@Service
class PersonClientService {

    @GrpcClient("local-grpc-server")
    private lateinit var personStub: PersonServiceBlockingStub

    fun getPerson(id: Int): PersonDto {
        try {
            val personId = PersonId.newBuilder().setId(id).build()
            val person = personStub.getPersonWithId(personId)
            return PersonDto(id, person.name, person.age)
        } catch (e: StatusRuntimeException) {
            throw e
        }
    }
}