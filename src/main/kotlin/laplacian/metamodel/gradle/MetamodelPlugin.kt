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
        project.configurations.getByName("implementation").dependencies.addAll(
            arrayOf(
                "laplacian:laplacian.generator:1.0.0",
                "laplacian:laplacian.model.metamodel:1.0.0"
            ).map{ project.dependencies.create(it) }
        )
        project.configurations.getByName("model").dependencies.addAll(
            arrayOf(
                "laplacian:laplacian.model.metamodel:1.0.0"
            ).map{ project.dependencies.create(it) }
        )
        project.configurations.getByName("template").dependencies.addAll(
            arrayOf(
                "laplacian:laplacian.template.entity.kotlin:1.0.0",
                "laplacian:laplacian.template.entity.json-schema:1.0.0"
            ).map{ project.dependencies.create(it) }
        )
    }
}
