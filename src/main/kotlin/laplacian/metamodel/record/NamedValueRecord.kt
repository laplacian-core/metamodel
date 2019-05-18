package laplacian.metamodel.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian.metamodel.model.NamedValue
import laplacian.util.*
/**
 * named_value
 */
data class NamedValueRecord (
    private val __record: Record,
    private val _context: Context,
    private val _record: Record = __record.normalizeCamelcase()
): NamedValue, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The name of this named_value.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The expression of this named_value.
     */
    override val expression: String
        get() = getOrThrow("expression")

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context) = records.map {
            NamedValueRecord(it, _context)
        }
    }
}