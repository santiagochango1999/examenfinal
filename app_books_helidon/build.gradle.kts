plugins {
    id("java")
    id("application")
}

group = "com.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.helidon:helidon-dependencies:4.0.11"))

    implementation("io.helidon.integrations.cdi:helidon-integrations-cdi-datasource-hikaricp")
    implementation("io.helidon.microprofile.server:helidon-microprofile-server")
    implementation("io.helidon.microprofile.rest-client", "helidon-microprofile-rest-client", "4.0.11")
    implementation("org.glassfish.jersey.media:jersey-media-json-binding")

    runtimeOnly("jakarta.persistence:jakarta.persistence-api")
    runtimeOnly("jakarta.transaction:jakarta.transaction-api")

    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-datasource-hikaricp")
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-hibernate")
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-jta-weld")
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-jpa")

    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.hibernate:hibernate-core:6.4.4.Final")

    implementation("io.helidon.microprofile.bundles:helidon-microprofile-core")
    implementation("io.helidon.microprofile.health:helidon-microprofile-health")
    implementation("io.helidon.microprofile:helidon-microprofile-cors")


    implementation("org.jboss:jandex:3.1.6")
}
tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        output.setResourcesDir(file("${buildDir}/classes/java/main"))
    }
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes(
            mapOf("Main-Class" to "com.distribuida.books.Main",
                "Class-Path" to configurations.runtimeClasspath
                    .get()
                    .joinToString(separator = " ") { file ->
                        "${file.name}"
                    })
        )
    }
    configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
}

tasks.distTar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
}

tasks.distZip {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
}
tasks.installDist{
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
}

tasks.named<JavaExec>("run") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.distribuida.books.Main")
}