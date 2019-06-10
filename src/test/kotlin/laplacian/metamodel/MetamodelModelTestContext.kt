package laplacian.metamodel

import laplacian.gradle.task.generate.ExecutionContext
import laplacian.gradle.task.generate.ProjectEntryResolver
import laplacian.metamodel.gradle.MetamodelModelEntryResolver
import laplacian.metamodel.model.EntityList
import laplacian.metamodel.model.ValueDomainTypeList
import java.io.File

class MetamodelModelTestContext {
    val entities: EntityList
        get() = executionContext.currentModel["entities"] as EntityList
    val valueDomainTypes: ValueDomainTypeList
        get() = executionContext.currentModel["value_domain_types"] as ValueDomainTypeList
    var executionContext: ExecutionContext = ExecutionContext().also { context ->
        val projectModel = File("laplacian-module.yml")
        if (projectModel.exists()) context.addModel(projectModel)
        context.addModelEntryResolver(
            MetamodelModelEntryResolver()
        )
    }

    fun models(vararg models: String): MetamodelModelTestContext {
        executionContext.addModel(*models).build()
        return this
    }
}