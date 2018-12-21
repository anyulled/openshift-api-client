package service

import config.EnvironmentEnum
import config.ServiceEnum

/**
 * Main Service
 */
class MainService(
    private val configMaps: ConfigMapsService = ConfigMapsService(),
    private val secrets: SecretsService = SecretsService(),
    private val deployTemplate: DeployTemplateService = DeployTemplateService(),
    private val deployConfiguration: DeployConfigurationService = DeployConfigurationService(),
    private val buildTemplate: BuildTemplateService = BuildTemplateService(),
    private val buildConfiguration: BuildConfigurationService = BuildConfigurationService(),
    private val image: ImageService = ImageService(),
    private val promotion: PromotionService = PromotionService()
) {

    suspend fun config(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean =
//        withContext(Dispatchers.Default) {
        configMaps.push(service, environment, token) &&
                secrets.push(service, environment, token)
//        }

    suspend fun deploy(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean =
//        withContext(Dispatchers.Default) {
            deployTemplate.push(service, environment, token) &&
                    deployTemplate.process(service, environment, token) &&
                    deployConfiguration.create(service, environment, token)
//        }

    suspend fun build(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean =
//        withContext(Dispatchers.Default) {
            buildTemplate.push(service, environment, token) &&
                    buildTemplate.process(service, environment, token) &&
                    buildConfiguration.create(service, environment, token)
//        }

    suspend fun publish(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean =
//        withContext(Dispatchers.Default) {
            configMaps.push(service, environment, token) &&
                    secrets.push(service, environment, token) &&
                    buildTemplate.push(service, environment, token) &&
                    buildTemplate.process(service, environment, token) &&
                    buildConfiguration.create(service, environment, token)
//        }

    suspend fun promote(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean =
//        withContext(Dispatchers.Default) {
            configMaps.push(service, environment, token) &&
                    secrets.push(service, environment, token) &&
                    image.push(service, environment, token) &&
                    image.process(service, environment, token) &&
                    promotion.push(service, environment, token)
//        }

    suspend fun nuke(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean =
//        withContext(Dispatchers.Default) {
            configMaps.delete(service, environment, token) &&
                    secrets.delete(service, environment, token) &&
                    buildTemplate.delete(service, environment, token) &&
                    buildConfiguration.delete(service, environment, token) &&
                    deployTemplate.delete(service, environment, token) &&
                    deployConfiguration.delete(service, environment, token) &&
                    image.delete(service, environment, token) &&
                    promotion.delete(service, environment, token)
//        }

    suspend fun rollout(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean =
//        withContext(Dispatchers.Default) {
            nuke(service, environment, token) &&
                    secrets.push(service, environment, token) &&
                    buildTemplate.push(service, environment, token) &&
                    buildTemplate.process(service, environment, token) &&
                    buildConfiguration.create(service, environment, token) &&
                    deployTemplate.push(service, environment, token) &&
                    deployTemplate.process(service, environment, token) &&
                    deployConfiguration.create(service, environment, token)
//        }

    suspend fun startBuild(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean =
//        withContext(Dispatchers.Default) {
        buildConfiguration.start(service, environment, token)
//        }

    suspend fun startDeploy(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean =
//        withContext(Dispatchers.Default) {
        deployConfiguration.start(service, environment, token)
//        }

    suspend fun startPush(service: ServiceEnum, environment: EnvironmentEnum, token: String): Boolean =
//        withContext(Dispatchers.Default) {
        image.start(service, environment, token)
//        }
}
