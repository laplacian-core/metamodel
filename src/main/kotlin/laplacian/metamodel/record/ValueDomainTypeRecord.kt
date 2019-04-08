package laplacian.metamodel.record
import com.github.jknack.handlebars.Context
import laplacian.metamodel.model.ValueDomainType

import laplacian.metamodel.model.ValueDomainTypeList


import laplacian.metamodel.model.ValueDomain


import laplacian.util.*

/**
 * value_domain_type
 */
data class ValueDomainTypeRecord (
    private val __record: Record,
    private val _context: Context,

    private val _record: Record = __record.normalizeCamelcase()
): ValueDomainType, Record by _record {

    /**
     * The name of this value_domain_type.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The type of this value_domain_type.
     */
    override val type: String
        get() = getOrThrow("type")

    /**
     * The description of this value_domain_type.
     */
    override val description: String
        get() = getOrThrow("description") {
            name
        }


    /**
     * domain
     */
    override val domain: ValueDomain
        = ValueDomainRecord(getOrThrow<Record>("domain"), _context)

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(_context: Context): ValueDomainTypeList {
            return _context.get("value_domain_types") as ValueDomainTypeList
        }
    }
}