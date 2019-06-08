package laplacian.metamodel.model


import laplacian.util.*

/**
 * relationship
 */
interface Relationship {
    /**
     * The name of this relationship.
     */
    val name: String
    /**
     * The identifier of this relationship.
     */
    val identifier: String
    /**
     * The cardinality of this relationship.
     */
    val cardinality: String
    /**
     * The reference_entity_name of this relationship.
     */
    val referenceEntityName: String
    /**
     * Defines this relationship is aggregate or not.
     */
    val aggregate: Boolean
    /**
     * Defines this relationship is inherited or not.
     */
    val inherited: Boolean
    /**
     * The reverse_of of this relationship.
     */
    val reverseOf: String?
    /**
     * The description of this relationship.
     */
    val description: String
    /**
     * The snippet of this relationship.
     */
    val snippet: String?
    /**
     * The class_name of this relationship.
     */
    val className: String
    /**
     * Defines this relationship is multiple or not.
     */
    val multiple: Boolean
    /**
     * Defines this relationship is allows_empty or not.
     */
    val allowsEmpty: Boolean
    /**
     * Defines this relationship is nullable or not.
     */
    val nullable: Boolean
    /**
     * The property_name of this relationship.
     */
    val propertyName: String
    /**
     * Defines this relationship is bidirectional or not.
     */
    val bidirectional: Boolean
    /**
     * Defines this relationship is recursive or not.
     */
    val recursive: Boolean
    /**
     * Defines this relationship is deprecated or not.
     */
    val deprecated: Boolean
    /**
     * The examples of this relationship.
     */
    val examples: List<Any>
    /**
     * entity
     */
    val entity: Entity
    /**
     * mappings
     */
    val mappings: List<PropertyMapping>
    /**
     * reference_entity
     */
    val referenceEntity: Entity
    /**
     * reverse
     */
    val reverse: Relationship?
        get() = referenceEntity.relationships.find{ it.name == reverseOf }
}