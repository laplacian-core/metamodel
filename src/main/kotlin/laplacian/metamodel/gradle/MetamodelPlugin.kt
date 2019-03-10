package laplacian.metamodel.gradle
import laplacian.gradle.task.LaplacianGenerateExtension
import laplacian.gradle.task.generate.ExecutionContext
import laplacian.gradle.task.generate.ModelEntryResolver
import laplacian.metamodel.model.EntityList
import laplacian.metamodel.model.ValueDomainTypeList
import laplacian.metamodel.record.EntityRecord
import laplacian.metamodel.record.ValueDomainTypeRecord
import laplacian.util.Record
import laplacian.util.RecordList
import laplacian.util.getList
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.lang.IllegalArgumentException

class MetamodelPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("laplacian.generator")
        val extension = project.extensions.getByType(LaplacianGenerateExtension::class.java)
        extension.model {
            modelEntryResolver(modelEntryResolver)
        }
    }
    companion object {
        val resolvesWhen = { key: String, model: Map<String, RecordList> ->
            arrayOf("entities", "value_domain_types").any { it == key }
        }
        val resolve = { key: String, model: Map<String, RecordList>, context: ExecutionContext ->
            when (key) {
                "entities" -> EntityList(
                    model.getList<Record>("entities").map{ EntityRecord(it, context.currentModel) },
                    context.currentModel
                )
                "value_domain_types" -> ValueDomainTypeList(
                    model.getList<Record>("value_domain_types").map{ ValueDomainTypeRecord(it, context.currentModel) },
                    context.currentModel
                )
                else -> throw IllegalArgumentException("Unknown key: $key")
            }
        }
        val modelEntryResolver = ModelEntryResolver(resolvesWhen, resolve)
    }
}
