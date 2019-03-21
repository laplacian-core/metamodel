package laplacian.model.metamodel.record
import com.github.jknack.handlebars.Context
import laplacian.model.metamodel.model.Entity
import laplacian.model.metamodel.model.EntityList
import laplacian.model.metamodel.model.Property
import laplacian.model.metamodel.model.Relationship
import laplacian.model.metamodel.model.Query
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
     * このエンティティのプロパティ
     */
    override val properties: List<Property>
        = PropertyRecord.from(getList("properties"), _context, this)
    /**
     * このエンティティと他のエンティティの関連
     */
    override val relationships: List<Relationship>
        = RelationshipRecord.from(getList("relationships", emptyList()), _context, this)
    /**
     * このエンティティに対するルートクエリ
     */
    override val queries: List<Query>
        = QueryRecord.from(getList("queries", emptyList()), _context, this)
    companion object {
        /**
         * creates record list from list of map
         */
        fun from(_context: Context): EntityList {
            return _context.get("entities") as EntityList
        }
    }
}