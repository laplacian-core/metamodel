package laplacian.metamodel.gradle
import laplacian.gradle.task.LaplacianGenerateExtension
import laplacian.metamodel.MetamodelModelLoader
import org.gradle.api.Plugin
import org.gradle.api.Project
class MetamodelPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("laplacian.generator")
        val extension = project.extensions.getByType(LaplacianGenerateExtension::class.java)
        extension.model {
            loader(MetamodelModelLoader())
        }
    }
}