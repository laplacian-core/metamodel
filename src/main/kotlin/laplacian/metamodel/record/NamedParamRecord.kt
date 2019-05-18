package laplacian.metamodel.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian.metamodel.model.NamedParam
import laplacian.util.*
/**
 * named_param
 */
data class NamedParamRecord (
    private val __record: Record,
    private val _context: Context,
    private val _record: Record = __record.normalizeCamelcase()
): NamedParam, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The name of this named_param.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The type of this named_param.
     */
    override val type: String
        get() = getOrThrow("type")

    /**
     * The description of this named_param.
     */
    override val description: String
        get() = getOrThrow("description") {
            name
        }

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context) = records.map {
            NamedParamRecord(it, _context)
        }
    }
}