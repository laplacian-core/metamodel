package laplacian.metamodel.model

import laplacian.metamodel.MetamodelModelTestContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class EntityTest {

    companion object {
        val MODEL_YAML = """
        |entities:
        |
        |- name: party
        |  namespace: example.party
        |  properties:
        |  - name: party_id
        |    type: string
        |    primary_key: true
        |  - name: party_type
        |    type: string
        |    subtype_key: true
        |
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
        |  relationships:
        |  - name: physical_characteristics
        |    reference_entity_name: physical_characteristic
        |    cardinality: '*'
        |    aggregate: true
        |
        |- name: organization
        |  namespace: example.party
        |  subtype_of: party
        |  properties:
        |  - name: name
        |    type: string
        |
        |- name: person_physical_characteristic
        |  namespace: example.party
        |  properties:
        |  - name: type
        |    type: string
        |  - name: from_date
        |    type: string
        |  - name: thru_date
        |    type: string
        |  - name: value
        |    type: string
        |  relationships:
        |  - name: person
        |    reference_entity_name: person
        |    cardinality: '1'
        |    reverse_of: physical_characteristics
        |  - name: characteristic_type
        |    reference_entity_name: person_physical_characteristic_type
        |    cardinality: '1'
        |    mappings:
        |    - from: type
        |      to: name
        |
        |- name: person_physical_characteristic_type
        |  namespace: example.party
        |  properties:
        |  - name: name
        |    type: string
        |    primary_key: true
        |  - name: description
        |    type: string
        """.trimMargin()
    }

    val entities: EntityList
        get() = MetamodelModelTestContext().models(MODEL_YAML).entities


    @Test
    fun `each properties of a entity model returns the appropriate value`() {
        val model = entities.find {
                it.name == "party" &&
                it.namespace == "example.party"
            }!!
            as Entity

        assertAll(
            { assertEquals("party", model.name) },
            { assertEquals("Party", model.className) },
            { assertEquals("party", model.subtypeKeyValue) },
            { assertEquals(true, model.topLevel) },
            { assertEquals("example.party.Party", model.fqn) },
            { assertEquals(listOf("party_id"), model.primaryKeyNames) }
        )
    }
    @Test
    fun `each properties of a entity model returns the appropriate value - alternative case 1`() {
        val model = entities.find {
                it.name == "person" &&
                it.namespace == "example.party"
            }!!
            as Entity

        assertAll(
            { assertEquals("person", model.name) },
            { assertEquals("person", model.subtypeKeyValue) },
            { assertEquals("example.party.Person", model.fqn) }
        )
    }
    @Test
    fun `each properties of a entity model returns the appropriate value - alternative case 2`() {
        val model = entities.find {
                it.name == "organization" &&
                it.namespace == "example.party"
            }!!
            as Entity

        assertAll(
            { assertEquals("organization", model.name) },
            { assertEquals("organization", model.subtypeKeyValue) }
        )
    }
    @Test
    fun `each properties of a entity model returns the appropriate value - alternative case 3`() {
        val model = entities.find {
                it.name == "person_physical_characteristic" &&
                it.namespace == "example.party"
            }!!
            as Entity

        assertAll(
        )
    }
    @Test
    fun `each properties of a entity model returns the appropriate value - alternative case 4`() {
        val model = entities.find {
                it.name == "person_physical_characteristic_type" &&
                it.namespace == "example.party"
            }!!
            as Entity

        assertAll(
        )
    }

    @Test
    fun `example of the relationship supertype 1`() {
        assertNull(
            entities.find{ it.name == "party" }?.supertype
        )
    }
    @Test
    fun `example of the relationship supertype 2`() {
        assertEquals(
            entities.find{ it.name == "person" }?.supertype?.name,
            "party"
        )
    }
}