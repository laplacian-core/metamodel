package laplacian.metamodel.model


import laplacian.util.*

/**
 * エンティティ
 */
interface Entity {
    /**
     * 名称
     */
    val name: String
    /**
     * 名前空間
     */
    val namespace: String
    /**
     * 識別子 省略時は名称を使用

     */
    val identifier: String
    /**
     * 詳細
     */
    val description: String
    /**
     * 値オブジェクトかどうか
     */
    val valueObject: Boolean
    /**
     * クラス名
     */
    val className: String
    /**
     * The name of the entity which this entity is subtype of

     */
    val subtypeOf: String?
    /**
     * The value of subtype key that represents this type of entity,
which is used when implementing polymorphism. The name of entity is used by default.

     */
    val subtypeKeyValue: String
    /**
     * Deprecated: prefer to use the 'reverse_of' property

     */
    val inherited: Boolean
    /**
     * このエンティティがトップレベルエンティティかどうか

     */
    val topLevel: Boolean
    /**
     * このエンティティがnamespaceをサポートしているかどうか

     */
    val supportsNamespace: Boolean
    /**
     * 完全修飾名

     */
    val fqn: String
    /**
     * 一意識別子となるカラム項目名のリスト

     */
    val primaryKeyNames: List<String>
    /**
     * Defines this entity is deprecated or not.
     */
    val deprecated: Boolean
    /**
     * examples which explain actual usage of this entity
     */
    val examples: List<Any>
    /**
     * The properties of this entity (excluding supertypes')
     */
    val properties: List<Property>
    /**
     * The properties of this entity
     */
    val allProperties: List<Property>
        get() = (supertype?.allProperties ?: emptyList()) + properties
    /**
     * The relationships with other entities (excluding supertypes')
     */
    val relationships: List<Relationship>
    /**
     * The relationships including supertype's ones.
     */
    val allRelationships: List<Relationship>
        get() = (supertype?.allRelationships ?: emptyList()) + relationships
    /**
     * The entity which this entity is subtype of
     */
    val supertype: Entity?
    /**
     * The entities which are supertype of this entity (recursive).

     */
    val ancestors: List<Entity>
        get() = mutableListOf<Entity>().also {
            var ancestor = supertype
            while (ancestor != null) {
                it.add(ancestor)
                ancestor = ancestor.supertype
            }
        }
    /**
     * The subtype entities of this entity
     */
    val subtypes: List<Entity>
    /**
     * All the subtypes of this entity
     */
    val descendants: List<Entity>
        get() = subtypes.flatMap{ listOf(it) + it.subtypes }
    /**
     * The property which is used to identify the type of a entity.
     */
    val subtypeKey: Property?
        get() = properties.find{ it.subtypeKey }
    /**
     * このエンティティに対するルートクエリ
     */
    val queries: List<Query>
    /**
     * 一意識別キーとなるプロパティのリスト
     */
    val primaryKeys: List<Property>
        get() = (supertype?.primaryKeys ?: emptyList()) + properties.filter{ it.primaryKey }
    /**
     * このエンティティの導出元エンティティ このエンティティが導出エンティティでなければ空集合

     */
    val inheritedFrom: List<Relationship>
        get() = supertype?.inheritedFrom ?: relationships.filter{ it.inherited }
    /**
     * owned_by
     */
    val ownedBy: Relationship?
        get() = supertype?.ownedBy ?: relationships.find{ it.reverse?.aggregate ?: false }
    /**
     * The relationship expresses the ownership of this entity

     */
    val ownership: Relationship?
        get() = ownedBy?.reverse
    /**
     * The entity this entity owns
     */
    val owner: Entity?
        get() = ownership?.entity
    /**
     * The aggregation tree this entity is owned

     */
    val ownershipHierarchy: List<Relationship>
        get() = supertype?.ownershipHierarchy ?:
            if (ownership == null) emptyList()
            else ownership!!.entity.ownershipHierarchy + ownership!!
    /**
     * root_owner
     */
    val rootOwner: Entity?
        get() = supertype?.rootOwner ?: owner?.rootOwner ?: owner
    /**
     * このエンティティが参照するエンティティの一覧(自身は除く)

     */
    val relatingEntities: List<Entity>
        get() = relationships
            .map{ it.referenceEntity }
            .filter{ it.fqn != this.fqn }
            .distinctBy{ it.fqn }
    /**
     * このエンティティが参照するトップレベルエンティティの一覧(自身は除く)
     */
    val relatingTopLevelEntities: List<Entity>
        get() = relatingEntities.filter{ !it.inherited }
    /**
     * このエンティティが参照する外部パッケージのエンティティ
     */
    val relatingExternalEntities: List<Entity>
        get() = relatingEntities.filter{ it.namespace != namespace }
    /**
     * このエンティティが管理する集約
     */
    val aggregates: List<Relationship>
        get() = relationships.filter{ it.aggregate }
    /**
     * このエンティティに集約されているエンティティの一覧 (再帰的に集約されているものを含む)

     */
    val aggregatedEntities: List<Entity>
        get() = (listOf(this) + aggregates.flatMap {
            it.referenceEntity.aggregatedEntities
        }).distinctBy{ it.fqn }
    /**
     * このエンティティが直接値を保持するプロパティ
     */
    val storedProperties: List<Property>
        get() = properties.filter{ it.snippet == null }
    /**
     * このエンティティが直接関連値を保持している関連
     */
    val storedRelationships: List<Relationship>
        get() = relationships.filter{ it.aggregate || it.mappings.isNotEmpty() }
}