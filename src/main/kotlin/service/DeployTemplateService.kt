package service

import config.EnvironmentEnum
import config.ServiceEnum
import utils.taskExecutor
import kotlin.random.Random

/**
 * Build Service
 */
class DeployTemplateService:Operations {
    private val name = "deploy template"

    override suspend fun push(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        return taskExecutor(environment, "push", service, Random.nextLong(2000L), name)
    }

    override suspend fun process(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        return taskExecutor(environment, "push", service, Random.nextLong(5000L), name)
    }

    override suspend fun create(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        return taskExecutor(environment, "push", service, Random.nextLong(1000L), name)
    }

    override suspend fun delete(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        return taskExecutor(environment, "push", service, Random.nextLong(1500L), name)
    }

    override suspend fun replace(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        return taskExecutor(environment, "push", service, Random.nextLong(2000L), name)
    }
}