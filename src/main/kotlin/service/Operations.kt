package service

import config.EnvironmentEnum
import config.ServiceEnum
import java.lang.UnsupportedOperationException

interface Operations {

    suspend fun create(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean = throw UnsupportedOperationException("Not Implemented")

    suspend fun delete(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean = throw UnsupportedOperationException("Not Implemented")

    suspend fun replace(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean = throw UnsupportedOperationException("Not Implemented")

    suspend fun push(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean = throw UnsupportedOperationException("Not Implemented")

    suspend fun process(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean = throw UnsupportedOperationException("Not Implemented")

    suspend fun start(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean = throw UnsupportedOperationException("Not Implemented")
}