package utils

import com.github.ajalt.clikt.output.TermUi.echo
import config.EnvironmentEnum
import config.ServiceEnum

fun info(environment: EnvironmentEnum, service: ServiceEnum, action: String) {
    echo("[${Thread.currentThread().name}] $environment - ${action.capitalize()} for ${service.toString().toLowerCase()}")
}

fun Boolean.completionStatus(): String {
    return if (this) {
        "successfully"
    } else {
        "with errors"
    }
}