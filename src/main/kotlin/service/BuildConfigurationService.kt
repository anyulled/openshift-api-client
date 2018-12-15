package service

import config.EnvironmentEnum
import config.ServiceEnum
import utils.taskExecutor
import kotlin.random.Random

/**
 * Build Service
 */
class BuildConfigurationService : Operations {
    private val name = "build configuration"

    override suspend fun push(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        return taskExecutor(environment, "push", service, Random.nextLong(1000L), name)
    }

    override suspend fun process(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        return taskExecutor(environment, "process", service, Random.nextLong(2500L), name)
    }

    override suspend fun create(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        return taskExecutor(environment, "create", service, Random.nextLong(1000L), name)
    }

    override suspend fun delete(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        return taskExecutor(environment, "delete", service, Random.nextLong(500L), name)
    }

    override suspend fun replace(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        return taskExecutor(environment, "replace", service, Random.nextLong(3000L), name)
    }
}