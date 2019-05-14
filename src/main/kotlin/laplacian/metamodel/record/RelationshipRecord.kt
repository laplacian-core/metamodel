package laplacian.metamodel.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian.metamodel.model.Relationship
import laplacian.metamodel.model.Entity
import laplacian.metamodel.model.PropertyMapping
import laplacian.util.*

/**
 * relationship
 */
data class RelationshipRecord (
    private val __record: Record,
    private val _context: Context,
    /**
     * the entity which aggregates this relationship
     */
    override val entity: Entity,
    private val _record: Record = __record.normalizeCamelcase()
): Relationship, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The name of this relationship.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The identifier of this relationship.
     */
    override val identifier: String
        get() = getOrThrow("identifier") {
            name.lowerUnderscorize()
        }

    /**
     * The cardinality of this relationship.
     */
    override val cardinality: String
        get() = getOrThrow("cardinality")

    /**
     * The reference_entity_name of this relationship.
     */
    override val referenceEntityName: String
        get() = getOrThrow("referenceEntityName")

    /**
     * Defines this relationship is aggregate or not.
     */
    override val aggregate: Boolean
        get() = getOrThrow("aggregate") {
            false
        }

    /**
     * Defines this relationship is inherited or not.
     */
    override val inherited: Boolean
        get() = getOrThrow("inherited") {
            false
        }

    /**
     * The description of this relationship.
     */
    override val description: String
        get() = getOrThrow("description") {
            name
        }

    /**
     * The snippet of this relationship.
     */
    override val snippet: String? by _record

    /**
     * mappings
     */
    override val mappings: List<PropertyMapping>
        = PropertyMappingRecord.from(getList("mappings", emptyList()), _context, this)

    /**
     * reference_entity
     */
    override val referenceEntity: Entity
        get() = EntityRecord.from(_context).find {
            it.name == referenceEntityName
        } ?: throw IllegalStateException(
            "There is no entity which meets the following condition(s): "
            + "Relationship.reference_entity_name == entity.name (=$referenceEntityName) "
            + "Possible values are: " + EntityRecord.from(_context).map {
              "(${ it.name })"
            }.joinToString()
        )

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, entity: Entity) = records.map {
            RelationshipRecord(it, _context, entity = entity)
        }
    }
}