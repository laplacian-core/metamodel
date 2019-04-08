package laplacian.metamodel.record
import com.github.jknack.handlebars.Context
import laplacian.metamodel.model.PropertyMapping


import laplacian.metamodel.model.Relationship


import laplacian.metamodel.model.Property


import laplacian.util.*

/**
 * property_mapping
 */
data class PropertyMappingRecord (
    private val __record: Record,
    private val _context: Context,

    /**
     * the relationship which aggregates this property_mapping
     */
    override val relationship: Relationship,

    private val _record: Record = __record.normalizeCamelcase()
): PropertyMapping, Record by _record {

    /**
     * The from of this property_mapping.
     */
    override val from: String
        get() = getOrThrow("from")

    /**
     * The to of this property_mapping.
     */
    override val to: String
        get() = getOrThrow("to")

    /**
     * The null_value of this property_mapping.
     */
    override val nullValue: String? by _record





    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, relationship: Relationship) = records.map {
            PropertyMappingRecord(it, _context, relationship = relationship)
        }
    }
}