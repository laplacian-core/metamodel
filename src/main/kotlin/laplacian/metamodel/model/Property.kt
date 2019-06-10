package laplacian.metamodel.model


import laplacian.util.*

/**
 * property
 */
interface Property {
    /**
     * The name of this property.
     */
    val name: String
    /**
     * The identifier of this property.
     */
    val identifier: String
    /**
     * Defines this property is primary_key or not.
     */
    val primaryKey: Boolean
    /**
     * Defines this property is subtype_key or not.
     */
    val subtypeKey: Boolean
    /**
     * The type of this property.
     */
    val type: String
    /**
     * 制約型名
     */
    val domainTypeName: String?
    /**
     * the maximum allowed size of the content of this property
     */
    val size: Int
    /**
     * Defines this property is optional or not.
     */
    val optional: Boolean
    /**
     * The description of this property.
     */
    val description: String
    /**
     * The default value of this property, which is used when the actual value is null

     */
    val defaultValue: String?
    /**
     * The example_values of this property.
     */
    val exampleValues: List<Any>
    /**
     * The table_column_name of this property.
     */
    val tableColumnName: String
    /**
     * The snippet of this property.
     */
    val snippet: String?
    /**
     * Defines this property is multiple or not.
     */
    val multiple: Boolean
    /**
     * The property_name of this property.
     */
    val propertyName: String
    /**
     * The class_name of this property.
     */
    val className: String
    /**
     * Whether this property permit a null value

     */
    val nullable: Boolean
    /**
     * Whether this preoperty overwrite a property which is defined in supertype.

     */
    val overwrites: Boolean
    /**
     * Defines this property is deprecated or not.
     */
    val deprecated: Boolean
    /**
     * entity
     */
    val entity: Entity
    /**
     * domain
     */
    val domain: ValueDomain?
    /**
     * domain_type
     */
    val domainType: ValueDomainType?
}