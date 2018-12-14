package config

enum class EnvironmentEnum(val url:String) {
    INT1("https://openshift.redhat.com:8440/oapi/v1"),
    INT2("https://openshift.redhat.com:8441/oapi/v1"),
    INT3("https://openshift.redhat.com:8442/oapi/v1"),
    INT4("https://openshift.redhat.com:8443/oapi/v1")
}