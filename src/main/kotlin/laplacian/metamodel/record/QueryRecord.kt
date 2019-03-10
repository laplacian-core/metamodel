package laplacian.metamodel.record
import com.github.jknack.handlebars.Context
import laplacian.metamodel.model.Query
import laplacian.metamodel.model.Entity
import laplacian.util.*
/**
 * The queries from which all navigation originates.
 */
data class QueryRecord (
    private val __record: Record,
    private val _context: Context,
    /**
     * the entity which aggregates this query
     */
    override val entity: Entity,
    private val _record: Record = __record.normalizeCamelcase()
): Query, Record by _record {
    /**
     * クエリ名称
     */
    override val name: String
        get() = getOrThrow("name")
    /**
     * 識別子
     */
    override val identifier: String
        get() = getOrThrow("identifier") {
            name.lowerUnderscorize()
        }
    /**
     * 結果型
     */
    override val type: String
        get() = getOrThrow("type") {
            resultEntity?.className?.let { className ->
                if (cardinality.contains("*")) "List<$className>" else className
            }
        }
    /**
     * クエリ結果エンティティ名
     */
    override val resultEntityName: String? by _record
    /**
     * 詳細
     */
    override val description: String
        get() = getOrThrow("description") {
            name
        }
    /**
     * 多重度
     */
    override val cardinality: String
        get() = getOrThrow("cardinality") {
            "*"
        }
    /**
     * クエリスクリプト
     */
    override val snippet: String
        get() = getOrThrow("snippet")
    /**
     * クエリ結果エンティティ
     */
    override val resultEntity: Entity?
        get() = EntityRecord.from(_context).find {
            it.name == resultEntityName
        }
    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, entity: Entity) = records.map {
            QueryRecord(it, _context, entity = entity)
        }
    }
}
