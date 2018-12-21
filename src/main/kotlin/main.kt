import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import config.ActionEnum
import config.ActionEnum.*
import config.EnvironmentEnum
import config.ServiceEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import service.MainService
import utils.completionStatus
import utils.info
import kotlin.system.measureTimeMillis

class Main : CliktCommand() {

    //region Input
    private val client: MainService = MainService()
    private val action: ActionEnum by option(help = "action to commit")
        .convert { ActionEnum.valueOf(it.toUpperCase()) }
        .default(ROLLOUT)
    private val environment: EnvironmentEnum by option(help = "Environment name")
        .convert { EnvironmentEnum.valueOf(it.toUpperCase()) }
        .default(EnvironmentEnum.INT1)
    private val services: List<ServiceEnum> by option(help = "Services to act on, comma separated")
        .convert {
            if (it.isEmpty()) ServiceEnum.values().toList()
            else it.replace(" ", "").split(",")
                .map { service -> ServiceEnum.valueOf(service.toUpperCase()) }
        }
        .default(listOf(ServiceEnum.DEAL, ServiceEnum.INSTRUMENT))
    private val token: String by option(help = "Authorization Token")
        .default("a9087as98d7as98d7s998")
    //endregion

    override fun run() {
        runBlocking(Dispatchers.Default) {
            val job = Job()
            val time = measureTimeMillis {
                services.map {
                    launch(Dispatchers.IO + job) {
                        info(environment, it, "Started ${action.name}")
                        val result =
                            when (action) {
                                CONFIG -> client.config(it, environment, token)
                                DEPLOY -> client.deploy(it, environment, token)
                                BUILD -> client.build(it, environment, token)
                                PUBLISH -> client.publish(it, environment, token)
                                PROMOTE -> client.promote(it, environment, token)
                                NUKE -> client.nuke(it, environment, token)
                                ROLLOUT -> client.rollout(it, environment, token)
                                START_BUILD -> client.startBuild(it, environment, token)
                                START_DEPLOY -> client.startDeploy(it, environment, token)
                                START_PUSH -> client.startPush(it, environment, token)
                            }
                        info(environment, it, "finished ${action.name} ${result.completionStatus()}")
                    }
                }
            }
            echo("operation completed in $time milliseconds")
            job.join()
        }
    }
}

fun main(args: Array<String>) = Main().main(args)