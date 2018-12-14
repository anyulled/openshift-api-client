package service

import config.EnvironmentEnum
import config.ServiceEnum
import utils.info

/**
 * Configuration Map Service
 */
class ConfigMapsService : Operations {
    private val name = "config map"

    override suspend fun push(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        info(environment, service, "push $name")
        return true
    }

    override suspend fun process(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        info(environment, service, "process $name")
        return true
    }

    override suspend fun create(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        info(environment, service, "create $name")
        return true
    }

    override suspend fun delete(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        info(environment, service, "delete $name")
        return true
    }


    override suspend fun replace(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean {
        info(environment, service, "replace $name")
        return true
    }

}
