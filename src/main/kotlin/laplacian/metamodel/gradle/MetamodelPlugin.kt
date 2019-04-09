package laplacian.metamodel.gradle
import laplacian.gradle.GeneratorPlugin
import laplacian.gradle.task.LaplacianGenerateExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class MetamodelPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply(GeneratorPlugin::class.java)
        val extension = project.extensions.getByType(LaplacianGenerateExtension::class.java)
        extension.model {
            modelEntryResolver(MetamodelModelEntryResolver())
        }
        manageDependentModules(project)
    }
    private fun manageDependentModules(project: Project) {
        val isLoadedByItSelf = { artifactId: String ->
            artifactId.contains(":${project.rootProject.name}:")
        }
        val dependentLibs = listOf(
            "laplacian:laplacian.model.metamodel:1.0.0",
            "laplacian:laplacian.model.metamodel:1.0.0",
            "laplacian:laplacian.generator:1.0.0"
        ).filter{ !isLoadedByItSelf(it) }
        val addToDependencies = { configName: String, dependencies :List<String> ->
            project.configurations.getByName(configName).dependencies.addAll(
                dependencies.map{ project.dependencies.create(it) }
            )
        }
        addToDependencies("implementation", dependentLibs)
    }
}