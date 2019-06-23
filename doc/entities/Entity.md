# **Entity**
**namespace:** laplacian.metamodel

エンティティ


example1:
```yaml
name: party
  namespace: example.party
  properties:
  - name: party_id
    type: string
    primary_key: true
  - name: party_type
    type: string
    subtype_key: true
```

example2:
```yaml
name: person
  namespace: example.party
  subtype_of: party
  properties:
  - name: last_name
    type: string
  - name: first_name
    type: string
  - name: middle_name
    type: string
    optional: true
  relationships:
  - name: physical_characteristics
    reference_entity_name: person_physical_characteristic
    cardinality: '*'
    aggregate: true
```

example3:
```yaml
name: organization
  namespace: example.party
  subtype_of: party
  properties:
  - name: name
    type: string
```

example4:
```yaml
name: person_physical_characteristic
  namespace: example.party
  properties:
  - name: type
    type: string
  - name: from_date
    type: string
  - name: thru_date
    type: string
  - name: value
    type: string
  relationships:
  - name: person
    reference_entity_name: person
    cardinality: '1'
    reverse_of: physical_characteristics
  - name: characteristic_type
    reference_entity_name: person_physical_characteristic_type
    cardinality: '1'
    mappings:
    - from: type
      to: name
```

example5:
```yaml
name: person_physical_characteristic_type
  namespace: example.party
  properties:
  - name: name
    type: string
    primary_key: true
  - name: description
    type: string
```


---

## Properties

### name: `String`
名称
- **Attributes:** *PK*

### namespace: `String`
名前空間
- **Attributes:** *PK*
- **Default Value:**
  ```kotlin
  if (inherited) {
      inheritedFrom.first().referenceEntity.namespace
  }
  else {
      _context.get("project.namespace") ?: throw IllegalStateException(
          "The ${name} entity does not have namespace." +
          "You should give it or set the default(project) namespace instead."
      )
  } as String
  ```

### identifier: `String`
識別子 省略時は名称を使用

- **Default Value:**
  ```kotlin
  name.lowerUnderscorize()
  ```

### description: `String`
詳細
- **Default Value:**
  ```kotlin
  name
  ```

### value_object: `Boolean`
値オブジェクトかどうか
- **Default Value:**
  ```kotlin
  false
  ```

### class_name: `String`
クラス名
- **Code:**
  ```kotlin
  identifier.upperCamelize()
  ```

### subtype_of: `String`
The name of the entity which this entity is subtype of


### subtype_key_value: `String`
The value of subtype key that represents this type of entity,
which is used when implementing polymorphism. The name of entity is used by default.

- **Default Value:**
  ```kotlin
  name
  ```

### inherited: `Boolean`
Deprecated: prefer to use the 'reverse_of' property

  *Warning: This relationship is deprecated and may be deleted in the future release.*

- **Code:**
  ```kotlin
  supertype?.inherited ?: relationships.any{ it.inherited || it.reverse != null }
  ```

### top_level: `Boolean`
このエンティティがトップレベルエンティティかどうか

- **Code:**
  ```kotlin
  !inherited && !valueObject
  ```

### supports_namespace: `Boolean`
このエンティティがnamespaceをサポートしているかどうか

- **Code:**
  ```kotlin
  properties.any { p ->
      p.name == "namespace" && p.type == "string"
  }
  ```

### fqn: `String`
完全修飾名

- **Code:**
  ```kotlin
  "$namespace.$className"
  ```

### primary_key_names: `List<String>`
一意識別子となるカラム項目名のリスト

- **Code:**
  ```kotlin
  inheritedFrom.flatMap { inheritance ->
      inheritance.referenceEntity.primaryKeys.map { pk ->
          "${inheritance.identifier.lowerUnderscorize()}_${pk.propertyName.lowerUnderscorize()}"
      }
  } + primaryKeys.map { it.propertyName.lowerUnderscorize() }
  ```

### deprecated: `Boolean`
Defines this entity is deprecated or not.
- **Default Value:**
  ```kotlin
  false
  ```

### examples: `List<Any>`
examples which explain actual usage of this entity
- **Default Value:**
  ```kotlin
  emptyList<Any>()
  ```

## Relationships

### properties: `List<Property>`
The properties of this entity (excluding supertypes')
- **Cardinality:** `*`

### all_properties: `List<Property>`
The properties of this entity
- **Cardinality:** `1..*`
- **Code:**
  ```kotlin
  (supertype?.allProperties ?: emptyList()) + properties
  ```

### relationships: `List<Relationship>`
The relationships with other entities (excluding supertypes')
- **Cardinality:** `*`

### all_relationships: `List<Relationship>`
The relationships including supertype's ones.
- **Cardinality:** `*`
- **Code:**
  ```kotlin
  (supertype?.allRelationships ?: emptyList()) + relationships
  ```

### supertype: `Entity?`
The entity which this entity is subtype of
- **Cardinality:** `0..1`
- **Examples:**
  ```kotlin
  entities.find{ it.name == "party" }?.supertype // -> null
  ```
  ```kotlin
  entities.find{ it.name == "person" }?.supertype?.name // -> "party"
  ```

### ancestors: `List<Entity>`
The entities which are supertype of this entity (recursive).

- **Cardinality:** `*`
- **Code:**
  ```kotlin
  mutableListOf<Entity>().also {
      var ancestor = supertype
      while (ancestor != null) {
          it.add(ancestor)
          ancestor = ancestor.supertype
      }
  }
  ```

### subtypes: `List<Entity>`
The subtype entities of this entity
- **Cardinality:** `*`

### descendants: `List<Entity>`
All the subtypes of this entity
- **Cardinality:** `*`
- **Code:**
  ```kotlin
  subtypes.flatMap{ listOf(it) + it.subtypes }
  ```

### subtype_key: `Property?`
The property which is used to identify the type of a entity.
- **Cardinality:** `0..1`
- **Code:**
  ```kotlin
  properties.find{ it.subtypeKey }
  ```

### queries: `List<Query>`
このエンティティに対するルートクエリ
- **Cardinality:** `*`

### primary_keys: `List<Property>`
一意識別キーとなるプロパティのリスト
- **Cardinality:** `1..*`
- **Code:**
  ```kotlin
  (supertype?.primaryKeys ?: emptyList()) + properties.filter{ it.primaryKey }
  ```

### inherited_from: `List<Relationship>`
このエンティティの導出元エンティティ このエンティティが導出エンティティでなければ空集合

  *Warning: This relationship is deprecated and may be deleted in the future release.*

- **Cardinality:** `*`
- **Code:**
  ```kotlin
  supertype?.inheritedFrom ?: relationships.filter{ it.inherited }
  ```

### ownership: `Relationship?`
The relationship expresses the ownership of this entity

- **Cardinality:** `0..1`
- **Code:**
  ```kotlin
  supertype?.ownership ?:
  relationships.find{ it.reverse?.aggregate ?: false }?.reverse
  ```

### owner: `Entity?`
The entity this entity owns
- **Cardinality:** `0..1`
- **Examples:**
  ```kotlin
  entities.find{ it.name == "person" }?.owner // -> null
  ```
  ```kotlin
  entities.find{ it.name == "person_physical_characteristic" }?.owner?.name // -> "person"
  ```
- **Code:**
  ```kotlin
  ownership?.entity
  ```

### ownership_hierarchy: `List<Relationship>`
The aggregation tree this entity is owned

- **Cardinality:** `*`
- **Code:**
  ```kotlin
  supertype?.ownershipHierarchy ?:
      if (ownership == null) emptyList()
      else ownership!!.entity.ownershipHierarchy + ownership!!
  ```

### root_owner: `Entity?`
root_owner
- **Cardinality:** `0..1`
- **Code:**
  ```kotlin
  supertype?.rootOwner ?: owner?.rootOwner ?: owner
  ```

### relating_entities: `List<Entity>`
このエンティティが参照するエンティティの一覧(自身は除く)

- **Cardinality:** `*`
- **Code:**
  ```kotlin
  relationships
      .map{ it.referenceEntity }
      .filter{ it.fqn != this.fqn }
      .distinctBy{ it.fqn }
  ```

### relating_top_level_entities: `List<Entity>`
このエンティティが参照するトップレベルエンティティの一覧(自身は除く)
- **Cardinality:** `*`
- **Code:**
  ```kotlin
  relatingEntities.filter{ !it.inherited }
  ```

### relating_external_entities: `List<Entity>`
このエンティティが参照する外部パッケージのエンティティ
- **Cardinality:** `*`
- **Code:**
  ```kotlin
  relatingEntities.filter{ it.namespace != namespace }
  ```

### aggregates: `List<Relationship>`
このエンティティが管理する集約
- **Cardinality:** `*`
- **Code:**
  ```kotlin
  relationships.filter{ it.aggregate }
  ```

### aggregated_entities: `List<Entity>`
このエンティティに集約されているエンティティの一覧 (再帰的に集約されているものを含む)

- **Cardinality:** `*`
- **Code:**
  ```kotlin
  (listOf(this) + aggregates.flatMap {
      it.referenceEntity.aggregatedEntities
  }).distinctBy{ it.fqn }
  ```

### stored_properties: `List<Property>`
このエンティティが直接値を保持するプロパティ
- **Cardinality:** `*`
- **Code:**
  ```kotlin
  properties.filter{ it.snippet == null }
  ```

### stored_relationships: `List<Relationship>`
このエンティティが直接関連値を保持している関連
- **Cardinality:** `*`
- **Code:**
  ```kotlin
  relationships.filter{ it.aggregate || it.mappings.isNotEmpty() }
  ```