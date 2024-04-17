plugins {
    kotlin("jvm") version "1.9.21"
    application
    id("org.jetbrains.dokka") version "1.9.10"
    id("app.cash.sqldelight") version "2.0.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // Koin, con BOM ya se instalan todas las dependencias necesarias con la versión correcta
    implementation(platform("io.insert-koin:koin-bom:3.5.3"))
    implementation("io.insert-koin:koin-core") // Core
    implementation("io.insert-koin:koin-test") // Para test y usar checkModules

    // Log
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    // SQLDelight para SQLite
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
    // Result ROP
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")

}

tasks.test {
    useJUnitPlatform()
}
tasks.jar {
    manifest {
        // definimos el punto de entrada del manifest
        // Es la clase que contiene el método main que queremos ejecutar
        // Recuerda que no se pone el punto del fichero, si no en mayusuculas
        // Tampoco se pone .class
        attributes["Main-Class"] = "MainKt"
    }
    // Añadimos las dependencias de compilación al jar
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    // configuramos la estrategia de duplicados
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
kotlin {
    jvmToolchain(8)
}

// Configuración del plugin de SqlDeLight
sqldelight {
    databases {
        // Nombre de la base de datos
        create("AppDatabase") {
            // Paquete donde se generan las clases
            packageName.set("org.example.database")
        }
    }
}

application {
    mainClass.set("MainKt")
}