package laplacian.metamodel.record
import com.github.jknack.handlebars.Context
import laplacian.metamodel.model.ValueItem
import laplacian.util.*
/**
 * value_item
 */
data class ValueItemRecord (
    private val __record: Record,
    private val _context: Context,
    private val _record: Record = __record.normalizeCamelcase()
): ValueItem, Record by _record {
    /**
     * The value of this value_item.
     */
    override val value: String
        get() = getOrThrow("value")
    /**
     * The label of this value_item.
     */
    override val label: String
        get() = getOrThrow("label") {
            value
        }
    /**
     * The description of this value_item.
     */
    override val description: String
        get() = getOrThrow("description") {
            "label (=$value)"
        }
    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context) = records.map {
            ValueItemRecord(it, _context)
        }
    }
}