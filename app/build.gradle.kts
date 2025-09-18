plugins {
    application
    jacoco
    id("org.sonarqube") version "6.2.0.5505"
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("hexlet.code.App")
}

tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "hexlet.code.App"
        )
    }
}

val junitBomVersion = "5.9.1"
val commonsLangVersion = "3.17.0"
val picocliVersion = "4.7.7"
val jacksonDatabindVersion = "2.13.4.2"
val commonsIoVersion = "2.14.0"
val jacksonDataformatYamlVersion = "2.13.3"

dependencies {
    testImplementation(platform("org.junit:junit-bom:$junitBomVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonDatabindVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonDataformatYamlVersion")
    implementation("commons-io:commons-io:$commonsIoVersion")
    implementation("info.picocli:picocli:$picocliVersion")
    implementation("org.apache.commons:commons-lang3:$commonsLangVersion")

    annotationProcessor("info.picocli:picocli-codegen:$picocliVersion")
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "9.0"
    configDirectory.set(file("config/checkstyle"))
}

tasks.withType<Checkstyle>().configureEach {
    classpath = files("${project.rootDir}/src/test/java")
}

val myCheckstyleTest by tasks.registering(Checkstyle::class) {
    source("src/test/java")
    classpath = files()
    configFile = file("${project.rootDir}/config/checkstyle/checkstyle.xml")
    include("**/*.java")
    exclude("**/generated/**")
}

tasks.named("check") {
    dependsOn(myCheckstyleTest)
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}


tasks.withType<Test> {
    finalizedBy(tasks.jacocoTestReport)
}

sonar {
    properties {
        property("sonar.projectKey", "F-Jahura_java-project-72")
        property("sonar.organization", "f-jahura")
        property("sonar.host.url", "https://sonarcloud.io")
        property ("sonar.login", "${System.getenv("SONAR_TOKEN")}")
        property("sonar.java.binaries", "${buildDir}/classes/java/main")
        property ("sonar.java.coveragePlugin", "jacoco")
        property ("sonar.coverage.jacoco.xmlReportPaths", "${buildDir}/reports/jacoco/test/jacocoTestReport.xml")
    }
}