package service

import config.EnvironmentEnum
import config.ServiceEnum
import kotlinx.coroutines.delay
import utils.info
import kotlin.random.Random

/**
 * Build Service
 */
class DeployConfigurationService : Operations {
    private val name = "deploy configuration"

    override suspend fun push(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        info(environment, service, "push $name")
        delay(Random.nextLong(2000L))
        return true
    }

    override suspend fun process(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        info(environment, service, "process $name")
        delay(Random.nextLong(5000L))
        return true
    }

    override suspend fun create(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        info(environment, service, "create $name")
        delay(Random.nextLong(1000L))
        return true
    }

    override suspend fun delete(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        info(environment, service, "delete $name")
        delay(Random.nextLong(500L))
        return true
    }

    override suspend fun replace(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        info(environment, service, "replace $name")
        delay(Random.nextLong(2000L))
        return true
    }
}