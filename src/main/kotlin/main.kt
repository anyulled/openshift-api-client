import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
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

    private val client: MainService = MainService()
    private val action: String by option(help = "action to commit").default("rollout")
    private val environment: String by option(help = "Environment name").default("INT1")
    private val services: String by option(help = "Services to act on, comma separated").default("")
    private val token: String by option(help = "Authorization Token").default("a9087as98d7as98d7s998")

    override fun run() {
        runBlocking(Dispatchers.Default) {
            val job = Job()
            //region Data parsing
            val serviceEnumList =
                if (services.isEmpty()) ServiceEnum.values().toList()
                else services.replace(" ", "").split(",")
                    .map { ServiceEnum.valueOf(it.toUpperCase()) }

            val actionEnum = valueOf(action.toUpperCase())
            val environmentEnum = EnvironmentEnum.valueOf(environment.toUpperCase())
            //endregion
            val time = measureTimeMillis {
                serviceEnumList.map {
                    launch(Dispatchers.IO + job) {
                        info(environmentEnum, it, "Started ${actionEnum.name}")
                        val result =
                            when (actionEnum) {
                                CONFIG -> client.config(it, environmentEnum, token)
                                DEPLOY -> client.deploy(it, environmentEnum, token)
                                BUILD -> client.build(it, environmentEnum, token)
                                PUBLISH -> client.publish(it, environmentEnum, token)
                                PROMOTE -> client.promote(it, environmentEnum, token)
                                NUKE -> client.nuke(it, environmentEnum, token)
                                ROLLOUT -> client.rollout(it, environmentEnum, token)
                                START_BUILD -> client.startBuild(it, environmentEnum, token)
                                START_DEPLOY -> client.startDeploy(it, environmentEnum, token)
                                START_PUSH -> client.startPush(it, environmentEnum, token)
                            }
                        info(environmentEnum, it, "finished ${actionEnum.name} ${result.completionStatus()}")
                    }
                }
            }
            echo("operation completed in $time milliseconds")
            job.join()
        }
    }
}

fun main(args: Array<String>) = Main().main(args)