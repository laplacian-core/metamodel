package laplacian.metamodel.template

import org.junit.jupiter.api.Test

class ModelEntryResolverTest {

    val template = assertion.withTemplate(
        "template/metamodel/src/main/kotlin/{path project.namespace}/gradle/{upper-camel project.name}ModelEntryResolver.kt.hbs"
    )

    @Test
    fun it_generates_the_list_wrapper_class_of_entity() {
        val toBeFile = "src/main/kotlin/laplacian/metamodel/gradle/MetamodelModelEntryResolver.kt"
        template.assertSameContent(toBeFile)
    }
}
