package utils

import com.github.ajalt.clikt.output.TermUi.echo
import config.EnvironmentEnum
import config.ServiceEnum
import kotlin.system.measureTimeMillis

fun info(environment: EnvironmentEnum, service: ServiceEnum, action: String) =
    echo("[${Thread.currentThread().name}] $environment - ${action.capitalize()} for ${service.toString().toLowerCase()}")

fun Boolean.completionStatus(): String = if (this) "successfully" else "with errors"

suspend fun taskExecutor(
    environment: EnvironmentEnum,
    task: String,
    service: ServiceEnum,
    delay: Long,
    name: String
): Boolean {
    val time = measureTimeMillis {
        info(environment, service, "start $task $name")
        kotlinx.coroutines.delay(delay)
    }
    info(environment, service, "finish $task $name in $time ms")
    return true
}