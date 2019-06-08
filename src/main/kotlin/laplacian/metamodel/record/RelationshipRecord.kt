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
     * The reverse_of of this relationship.
     */
    override val reverseOf: String? by _record

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
     * The class_name of this relationship.
     */
    override val className: String
        get() = if (multiple) "List<${referenceEntity.className}>" else (referenceEntity.className + if (nullable) "?" else "")

    /**
     * Defines this relationship is multiple or not.
     */
    override val multiple: Boolean
        get() = cardinality.contains("""(\*|N|\.\.[2-9][0-9]+)""".toRegex())

    /**
     * Defines this relationship is allows_empty or not.
     */
    override val allowsEmpty: Boolean
        get() = cardinality == "N" || cardinality == "*" || cardinality.contains("""(0\.\.)""".toRegex())

    /**
     * Defines this relationship is nullable or not.
     */
    override val nullable: Boolean
        get() = !multiple && allowsEmpty

    /**
     * The property_name of this relationship.
     */
    override val propertyName: String
        get() = identifier.lowerCamelize()

    /**
     * Defines this relationship is bidirectional or not.
     */
    override val bidirectional: Boolean
        get() = aggregate && referenceEntity.relationships.any {
            it.inherited && (it.referenceEntity.fqn == this.entity.fqn)
        }

    /**
     * Defines this relationship is recursive or not.
     */
    override val recursive: Boolean
        get() = aggregate && (referenceEntity.fqn == entity.fqn)

    /**
     * Defines this relationship is deprecated or not.
     */
    override val deprecated: Boolean
        get() = getOrThrow("deprecated") {
            false
        }

    /**
     * The examples of this relationship.
     */
    override val examples: List<Any>
        get() = getOrThrow("examples") {
            emptyList<Any>()
        }

    /**
     * mappings
     */
    override val mappings: List<PropertyMapping>
        = PropertyMappingRecord.from(_record.getList("mappings", emptyList()), _context, this)

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
        fun from(records: RecordList, _context: Context, entity: Entity) = records
            .mergeWithKeys("name")
            .map {
                RelationshipRecord(it, _context, entity = entity)
            }
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RelationshipRecord) return false
        if (entity != other.entity) return false
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        var result = entity.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "RelationshipRecord(" +
            "entity=$entity, " +
            "name=$name" +
        ")"
    }
}