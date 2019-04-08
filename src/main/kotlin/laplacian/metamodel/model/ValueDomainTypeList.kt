package laplacian.metamodel.model
import com.github.jknack.handlebars.Context

import laplacian.util.*

/**
 * A container for records of value_domain_type
 */
class ValueDomainTypeList(
    list: List<ValueDomainType>,
    val context: Context
) : List<ValueDomainType> by list {
}