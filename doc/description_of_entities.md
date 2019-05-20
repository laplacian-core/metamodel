## Description of entities

### Structure
![](./image/model-diagram.svg)



---
### **entity** (*laplacian.metamodel.model.Entity*)
  エンティティ

#### Properties
* **name:** *PK* `String`
  名称
* **namespace:** *PK* `String?`
  名前空間
* **identifier:** `String?`
  識別子 省略時は名称を使用
* **description:** `String?`
  詳細
* **value_object:** `Boolean?`
  値オブジェクトかどうか
* **class_name:** `String`
  クラス名
* **subtype_of:** `String?`
  The name of the entity which this entity is subtype of
* **subtype_key_value:** `String?`
  The value of subtype key that represents this type of entity,
  which is used when implementing polymorphism. The name of entity is used by default.
* **inherited:** `Boolean`
  他のエンティティの導出エンティティであるかどうか
* **top_level:** `Boolean`
  このエンティティがトップレベルエンティティかどうか
* **supports_namespace:** `Boolean`
  このエンティティがnamespaceをサポートしているかどうか
* **fqn:** `String`
  完全修飾名
* **primary_key_names:** `List<String>`
  一意識別子となるカラム項目名のリスト

#### Relationships
* **properties:** `List<Property>`
  The properties of this entity (excluding supertypes')
* **all_properties:** `List<Property>`
  The properties of this entity
* **relationships:** `List<Relationship>`
  The relationships with other entities (excluding supertypes')
* **all_relationships:** `List<Relationship>`
  The relationships including supertype's ones.
* **supertype:** `Entity?`
  The entity which this entity is subtype of
* **ancestors:** `List<Entity>`
  The entities which are supertype of this entity (recursive).
* **subtypes:** `List<Entity>`
  The subtype entities of this entity
* **descendants:** `List<Entity>`
  All the subtypes of this entity
* **subtype_key:** `Property?`
  The property which is used to identify the type of a entity.
* **queries:** `List<Query>`
  このエンティティに対するルートクエリ
* **primary_keys:** `List<Property>`
  一意識別キーとなるプロパティのリスト
* **inherited_from:** `List<Relationship>`
  このエンティティの導出元エンティティ このエンティティが導出エンティティでなければ空集合
* **relating_entities:** `List<Entity>`
  このエンティティが参照するエンティティの一覧(自身は除く)
* **relating_top_level_entities:** `List<Entity>`
  このエンティティが参照するトップレベルエンティティの一覧(自身は除く)
* **relating_external_entities:** `List<Entity>`
  このエンティティが参照する外部パッケージのエンティティ
* **aggregates:** `List<Relationship>`
  このエンティティが管理する集約
* **aggregated_entities:** `List<Entity>`
  このエンティティに集約されているエンティティの一覧 (再帰的に集約されているものを含む)
* **stored_properties:** `List<Property>`
  このエンティティが直接値を保持するプロパティ
* **stored_relationships:** `List<Relationship>`
  このエンティティが直接関連値を保持している関連




---
### **property** (*laplacian.metamodel.model.Property*)
  property

#### Properties
* **name:** *PK* `String`
  The name of this property.
* **identifier:** `String?`
  The identifier of this property.
* **primary_key:** `Boolean?`
  Defines this property is primary_key or not.
* **subtype_key:** `Boolean?`
  Defines this property is subtype_key or not.
* **type:** `String`
  The type of this property.
* **domain_type_name:** `String?`
  制約型名
* **size:** `Int?`
  the maximum allowed size of the content of this property
* **optional:** `Boolean?`
  Defines this property is optional or not.
* **description:** `String?`
  The description of this property.
* **default_value:** `String?`
  The default value of this property, which is used when the actual value is null
* **example_value:** `String?`
  The example_value of this property.
* **table_column_name:** `String?`
  The table_column_name of this property.
* **snippet:** `String?`
  The snippet of this property.
* **multiple:** `Boolean?`
  Defines this property is multiple or not.
* **property_name:** `String`
  The property_name of this property.
* **class_name:** `String`
  The class_name of this property.
* **nullable:** `Boolean`
  Whether this property permit a null value
* **overwrites:** `Boolean`
  Whether this preoperty overwrite a property which is defined in supertype.

#### Relationships
* **entity:** `Entity`
  entity
* **domain:** `ValueDomain?`
  domain
* **domain_type:** `ValueDomainType?`
  domain_type




---
### **relationship** (*laplacian.metamodel.model.Relationship*)
  relationship

#### Properties
* **name:** *PK* `String`
  The name of this relationship.
* **identifier:** `String?`
  The identifier of this relationship.
* **cardinality:** `String`
  The cardinality of this relationship.
* **reference_entity_name:** `String`
  The reference_entity_name of this relationship.
* **aggregate:** `Boolean?`
  Defines this relationship is aggregate or not.
* **inherited:** `Boolean?`
  Defines this relationship is inherited or not.
* **description:** `String?`
  The description of this relationship.
* **snippet:** `String?`
  The snippet of this relationship.
* **class_name:** `String`
  The class_name of this relationship.
* **multiple:** `Boolean`
  Defines this relationship is multiple or not.
* **allows_empty:** `Boolean`
  Defines this relationship is allows_empty or not.
* **nullable:** `Boolean`
  Defines this relationship is nullable or not.
* **property_name:** `String`
  The property_name of this relationship.
* **bidirectional:** `Boolean`
  Defines this relationship is bidirectional or not.
* **recursive:** `Boolean`
  Defines this relationship is recursive or not.

#### Relationships
* **entity:** `Entity`
  entity
* **mappings:** `List<PropertyMapping>`
  mappings
* **reference_entity:** `Entity`
  reference_entity




---
### **query** (*laplacian.metamodel.model.Query*)
  The queries from which all navigation originates.

#### Properties
* **name:** `String`
  クエリ名称
* **identifier:** `String?`
  識別子
* **type:** `String?`
  結果型
* **result_entity_name:** `String?`
  クエリ結果エンティティ名
* **description:** `String?`
  詳細
* **cardinality:** `String?`
  多重度
* **snippet:** `String`
  クエリスクリプト
* **oneliner:** `Boolean`
  Defines this query is oneliner or not.

#### Relationships
* **entity:** `Entity`
  エンティティ
* **result_entity:** `Entity?`
  クエリ結果エンティティ




---
### **value_domain_type** (*laplacian.metamodel.model.ValueDomainType*)
  value_domain_type

#### Properties
* **name:** `String`
  The name of this value_domain_type.
* **type:** `String`
  The type of this value_domain_type.
* **description:** `String?`
  The description of this value_domain_type.

#### Relationships
* **domain:** `ValueDomain`
  domain




---
### **value_domain** (*laplacian.metamodel.model.ValueDomain*)
  value_domain

#### Properties
* **pattern:** `String?`
  許容される値のパターン(正規表現)

#### Relationships
* **choices:** `List<ValueItem>`
  許容される値のリスト