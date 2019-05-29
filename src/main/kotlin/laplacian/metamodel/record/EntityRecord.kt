package laplacian.metamodel.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian.metamodel.model.Entity
import laplacian.metamodel.model.EntityList
import laplacian.metamodel.model.Property
import laplacian.metamodel.model.Relationship
import laplacian.metamodel.model.Query
import laplacian.util.*
/**
 * エンティティ
 */
data class EntityRecord (
    private val __record: Record,
    private val _context: Context,
    private val _record: Record = __record.normalizeCamelcase()
): Entity, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * 名称
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * 名前空間
     */
    override val namespace: String
        get() = getOrThrow("namespace") {
            if (inherited) {
                inheritedFrom.first().referenceEntity.namespace
            }
            else {
                _context.get("project.namespace") ?: throw IllegalStateException(
                    "The ${name} entity does not have namespace." +
                    "You should give it or set the default(project) namespace instead."
                )
            } as String
        }

    /**
     * 識別子 省略時は名称を使用

     */
    override val identifier: String
        get() = getOrThrow("identifier") {
            name.lowerUnderscorize()
        }

    /**
     * 詳細
     */
    override val description: String
        get() = getOrThrow("description") {
            name
        }

    /**
     * 値オブジェクトかどうか
     */
    override val valueObject: Boolean
        get() = getOrThrow("valueObject") {
            false
        }

    /**
     * クラス名
     */
    override val className: String
        get() = identifier.upperCamelize()

    /**
     * The name of the entity which this entity is subtype of

     */
    override val subtypeOf: String? by _record

    /**
     * The value of subtype key that represents this type of entity,
which is used when implementing polymorphism. The name of entity is used by default.

     */
    override val subtypeKeyValue: String
        get() = getOrThrow("subtypeKeyValue") {
            name
        }

    /**
     * 他のエンティティの導出エンティティであるかどうか

     */
    override val inherited: Boolean
        get() = supertype?.inherited ?: relationships.any{ it.inherited }

    /**
     * このエンティティがトップレベルエンティティかどうか

     */
    override val topLevel: Boolean
        get() = !inherited && !valueObject

    /**
     * このエンティティがnamespaceをサポートしているかどうか

     */
    override val supportsNamespace: Boolean
        get() = properties.any { p ->
            p.name == "namespace" && p.type == "string"
        }

    /**
     * 完全修飾名

     */
    override val fqn: String
        get() = "$namespace.$className"

    /**
     * 一意識別子となるカラム項目名のリスト

     */
    override val primaryKeyNames: List<String>
        get() = inheritedFrom.flatMap { inheritance ->
            inheritance.referenceEntity.primaryKeys.map { pk ->
                "${inheritance.identifier.lowerUnderscorize()}_${pk.propertyName.lowerUnderscorize()}"
            }
        } + primaryKeys.map { it.propertyName.lowerUnderscorize() }

    /**
     * Defines this entity is deprecated or not.
     */
    override val deprecated: Boolean
        get() = getOrThrow("deprecated") {
            false
        }

    /**
     * The properties of this entity (excluding supertypes')
     */
    override val properties: List<Property>
        = PropertyRecord.from(_record.getList("properties", emptyList()), _context, this)

    /**
     * The relationships with other entities (excluding supertypes')
     */
    override val relationships: List<Relationship>
        = RelationshipRecord.from(_record.getList("relationships", emptyList()), _context, this)

    /**
     * The entity which this entity is subtype of
     */
    override val supertype: Entity?
        get() = EntityRecord.from(_context).find {
            it.name == subtypeOf
        }

    /**
     * The subtype entities of this entity
     */
    override val subtypes: List<Entity>
        get() = EntityRecord.from(_context).filter {
            it.subtypeOf == name
        }

    /**
     * このエンティティに対するルートクエリ
     */
    override val queries: List<Query>
        = QueryRecord.from(_record.getList("queries", emptyList()), _context, this)

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(_context: Context): EntityList {
            return _context.get("entities") as EntityList
        }
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EntityRecord) return false
        if (name != other.name) return false
        if (namespace != other.namespace) return false
        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + namespace.hashCode()
        return result
    }

    override fun toString(): String {
        return "EntityRecord(" +
            "name=$name, " +
            "namespace=$namespace" +
        ")"
    }
}