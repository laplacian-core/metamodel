package laplacian.metamodel.record
import com.github.jknack.handlebars.Context
import laplacian.metamodel.model.ValueDomain


import laplacian.metamodel.model.ValueItem


import laplacian.util.*

/**
 * value_domain
 */
data class ValueDomainRecord (
    private val __record: Record,
    private val _context: Context,

    private val _record: Record = __record.normalizeCamelcase()
): ValueDomain, Record by _record {

    /**
     * 許容される値のパターン(正規表現)
     */
    override val pattern: String? by _record


    /**
     * 許容される値のリスト
     */
    override val choices: List<ValueItem>
        = ValueItemRecord.from(getList("choices", emptyList()), _context)

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context) = records.map {
            ValueDomainRecord(it, _context)
        }
    }
}