import laplacian.gradle.task.LaplacianGenerateExtension

group = "laplacian"
version = "1.0.0"

plugins {
    `maven-publish`
    `java-gradle-plugin`
    kotlin("jvm") version "1.3.10"
    id("laplacian.model.metamodel") version "1.0.0"
}

repositories {
    maven { url = uri("../mvn-repo/") }
    maven { url = uri("https://github.com/nabla-squared/raw/mvn-repo/") }
    jcenter()
}

dependencies {
    template("laplacian:laplacian.template.entity.kotlin:1.0.0")
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("laplacian:laplacian.generator:1.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.2.0")
}

configure<LaplacianGenerateExtension> {
    model {
        dir("src/main/resources")
    }
}

gradlePlugin {
    plugins {
        create("laplacianPlugin") {
            id = "laplacian.model.metamodel"
            implementationClass = "laplacian.metamodel.gradle.MetamodelPlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            url = uri("$projectDir/../maven2/")
        }
    }
    publications.create("mavenJava", MavenPublication::class.java) {
        from(components["java"])
    }
}
