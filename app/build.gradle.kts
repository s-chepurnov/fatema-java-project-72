plugins {
    application
    jacoco
    id("org.sonarqube") version "6.3.1.5724"
    checkstyle
    id("com.github.johnrengelman.shadow") version "8.1.1"
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
val picocliVersion = "4.7.7"
val commonsIoVersion = "2.14.0"
val commonsLangVersion = "3.17.0"

dependencies {
    implementation("org.apache.commons:commons-lang3:$commonsLangVersion")
    implementation ("org.apache.commons:commons-text:1.9")
    implementation ("commons-codec:commons-codec:1.18.0")
    implementation("commons-io:commons-io:$commonsIoVersion")

    implementation("io.javalin:javalin:6.6.0")
    implementation("io.javalin:javalin-bundle:6.6.0")
    implementation("io.javalin:javalin-rendering:6.6.0")
    implementation("gg.jte:jte:3.1.9")

    implementation("org.slf4j:slf4j-simple:2.0.17")

    implementation ("net.datafaker:datafaker:2.3.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation ("com.google.guava:guava:31.1-jre")

    implementation("com.h2database:h2:2.2.220")
    implementation("com.zaxxer:HikariCP:5.0.1")

    implementation("info.picocli:picocli:$picocliVersion")

    implementation("jakarta.servlet:jakarta.servlet-api:6.1.0")
    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.2")
    implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:3.0.1")

    annotationProcessor("info.picocli:picocli-codegen:$picocliVersion")
    annotationProcessor ("org.projectlombok:lombok:1.18.38")

    compileOnly ("org.projectlombok:lombok:1.18.38")

    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation(platform("org.junit:junit-bom:$junitBomVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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
