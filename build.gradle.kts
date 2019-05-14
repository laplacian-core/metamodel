group = "laplacian"
version = "1.0.0"

plugins {
    `maven-publish`
    `java-gradle-plugin`
    kotlin("jvm") version "1.3.10"
    id("laplacian.model.metamodel") version "1.0.0"
}

repositories {
    maven(url = "../mvn-repo/")
    maven(url = "https://raw.github.com/nabla-squared/mvn-repo/master/")
    jcenter()
}




dependencies {
    template("laplacian:laplacian.template.entity.kotlin:1.0.0")
    template("laplacian:laplacian.template.entity.json-schema:1.0.0")
    template("laplacian:laplacian.template.entity.document:1.0.0")
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.2.0")
}
gradlePlugin {
    plugins {
        create("MetamodelPlugin") {
            id = "laplacian.model.metamodel"
            implementationClass = "laplacian.metamodel.gradle.MetamodelPlugin"
        }
    }
}

val sourcesJar by tasks.register<Jar>("sourceJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val modelJar by tasks.register<Jar>("modelJar") {
    from(laplacianGenerate.modelSpec.get().files)
    exclude("laplacian-module.*")
}

publishing {
    repositories {
        maven(url = "../mvn-repo/")
    }
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "laplacian.model.metamodel"
            artifact(sourcesJar)
        }
        create<MavenPublication>("model") {
            artifactId = "laplacian.model-def.metamodel"
            artifact(modelJar)
        }
    }
}