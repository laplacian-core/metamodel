import laplacian.gradle.task.LaplacianGenerateExtension
import laplacian.metamodel.MetamodelModelLoader

group = "laplacian"
version = "1.0.0"

buildscript {
    repositories {
        maven { url = uri("../maven2/") }
        maven { url = uri("https://bitbucket.org/nabla2/maven2/raw/master/") }
        jcenter()
    }
    dependencies {
        classpath("laplacian:laplacian.generator:1.0.0")
        classpath("laplacian:laplacian.model.metamodel:1.0.0")
    }
}

defaultTasks = listOf("build")

repositories {
    maven { url = uri("../maven2/") }
    maven { url = uri("https://bitbucket.org/nabla2/maven2/raw/master/") }
    jcenter()
}

plugins {
    kotlin("jvm") version "1.3.10"
	`maven-publish`
    id("laplacian.generator") version "1.0.0"
}

//val laplacianTemplate = configurations.create("laplacianTemplate")

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
        loader(MetamodelModelLoader())
        dir("src/main/resources")
    }
    templateModule {
        from("laplacian.template.entity.kotlin")
        into("./")
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
