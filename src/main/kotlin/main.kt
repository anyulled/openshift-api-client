import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import config.ActionEnum.*
import config.EnvironmentEnum
import config.ServiceEnum
import service.MainService
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.*
import utils.completionStatus
import utils.info

class Main : CliktCommand() {

    private val client: MainService = MainService()
    private val action: String by option(help = "action to commit").default("check")
    private val environment: String by option(help = "Environment name").default("INT1")
    private val services: String by option(help = "Services to act on, comma separated").default("")
    private val token: String by option(help = "Authorization Token").default("a9087as98d7as98d7s998")

    override fun run() {
        runBlocking {
            val serviceEnumList =
                if (services.isEmpty()) ServiceEnum.values().toList()
                else services.replace(" ", "").split(",")
                    .map { ServiceEnum.valueOf(it.toUpperCase()) }

            val actionEnum = valueOf(action.toUpperCase())
            val environmentEnum = EnvironmentEnum.valueOf(environment.toUpperCase())

            val time = measureTimeMillis {

                for (service in serviceEnumList) {
                    info(environmentEnum, service, "Started ${actionEnum.name}")
//                    echo(":: $environment - Performing $actionEnum for ${service.toString().toLowerCase()} service ::")
                    val result = GlobalScope.async {
                        when (actionEnum) {
                            CONFIG ->  client.config(service, environmentEnum, token)
                            DEPLOY -> client.deploy(service, environmentEnum, token)
                            BUILD -> client.build(service, environmentEnum, token)
                            PUBLISH -> client.publish(service, environmentEnum, token)
                            PROMOTE -> client.promote(service, environmentEnum, token)
                            NUKE -> client.nuke(service, environmentEnum, token)
                            ROLLOUT -> client.rollout(service, environmentEnum, token)
                            START_BUILD -> client.startBuild(service, environmentEnum, token)
                            START_DEPLOY -> client.startDeploy(service, environmentEnum, token)
                            START_PUSH -> client.startPush(service, environmentEnum, token)
                        }
                    }
                    info(environmentEnum, service, "finished ${actionEnum.name} ${result.await().completionStatus()}")
//                    echo(":: $environment - Finished $actionEnum for ${service.toString().toLowerCase()} service ${result.await().completionStatus()} ::")
                }
            }
            echo("operation completed in $time milliseconds")
        }
    }


}

fun main(args: Array<String>) = Main().main(args)