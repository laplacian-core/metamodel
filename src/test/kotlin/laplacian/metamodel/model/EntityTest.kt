package laplacian.metamodel.model

import laplacian.metamodel.MetamodelModelTestContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class EntityTest {


    @Test
    fun `each properties of a entity model returns the appropriate value`() {
        val context = MetamodelModelTestContext().models(
        """
        |entities:
        |- name: party
        |  namespace: example.party
        |  properties:
        |  - name: party_id
        |    type: string
        |    primary_key: true
        |  - name: party_type
        |    type: string
        |    subtype_key: true
        """.trimMargin())
        val model = context
            .entities.find {
                it.name == "party" &&
                it.namespace == "example.party"
            }!!
            as Entity

        assertAll(
            { assertEquals("party", model.name) },
            { assertEquals("Party", model.className) },
            { assertEquals("party", model.subtypeKeyValue) }
        )
    }
    @Test
    fun `each properties of a entity model returns the appropriate value - alternative case 1`() {
        val context = MetamodelModelTestContext().models(
        """
        |entities:
        |- name: person
        |  namespace: example.party
        |  subtype_of: party
        |  properties:
        |  - name: last_name
        |    type: string
        |  - name: first_name
        |    type: string
        |  - name: middle_name
        |    type: string
        |    optional: true
        """.trimMargin())
        val model = context
            .entities.find {
                it.name == "person" &&
                it.namespace == "example.party"
            }!!
            as Entity

        assertAll(
            { assertEquals("person", model.name) },
            { assertEquals("person", model.subtypeKeyValue) }
        )
    }
    @Test
    fun `each properties of a entity model returns the appropriate value - alternative case 2`() {
        val context = MetamodelModelTestContext().models(
        """
        |entities:
        |- name: organization
        |  namespace: example.party
        |  subtype_of: party
        |  properties:
        |  - name: name
        |    type: string
        """.trimMargin())
        val model = context
            .entities.find {
                it.name == "organization" &&
                it.namespace == "example.party"
            }!!
            as Entity

        assertAll(
            { assertEquals("organization", model.name) },
            { assertEquals("organization", model.subtypeKeyValue) }
        )
    }
}