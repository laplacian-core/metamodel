# **Relationship**
**namespace:** laplacian.metamodel

relationship



---

## Properties

### name: `String`
The name of this relationship.
- **Attributes:** *PK*

### identifier: `String`
The identifier of this relationship.
- **Default Value:**
  ```kotlin
  name.lowerUnderscorize()
  ```

### cardinality: `String`
The cardinality of this relationship.

### reference_entity_name: `String`
The reference_entity_name of this relationship.

### aggregate: `Boolean`
Defines this relationship is aggregate or not.
- **Default Value:**
  ```kotlin
  false
  ```

### inherited: `Boolean`
Defines this relationship is inherited or not.
  *Warning: This relationship is deprecated and may be deleted in the future release.*

- **Default Value:**
  ```kotlin
  reverseOf != null
  ```

### reverse_of: `String`
The reverse_of of this relationship.

### description: `String`
The description of this relationship.
- **Default Value:**
  ```kotlin
  name
  ```

### snippet: `String`
The snippet of this relationship.

### class_name: `String`
The class_name of this relationship.
- **Code:**
  ```kotlin
  if (multiple) "List<${referenceEntity.className}>" else (referenceEntity.className + if (nullable) "?" else "")
  ```

### multiple: `Boolean`
Defines this relationship is multiple or not.
- **Code:**
  ```kotlin
  cardinality.contains("""(\*|N|\.\.[2-9][0-9]+)""".toRegex())
  ```

### allows_empty: `Boolean`
Defines this relationship is allows_empty or not.
- **Code:**
  ```kotlin
  cardinality == "N" || cardinality == "*" || cardinality.contains("""(0\.\.)""".toRegex())
  ```

### nullable: `Boolean`
Defines this relationship is nullable or not.
- **Code:**
  ```kotlin
  !multiple && allowsEmpty
  ```

### property_name: `String`
The property_name of this relationship.
- **Code:**
  ```kotlin
  identifier.lowerCamelize()
  ```

### bidirectional: `Boolean`
Defines this relationship is bidirectional or not.
- **Code:**
  ```kotlin
  aggregate && referenceEntity.relationships.any {
      it.inherited && (it.referenceEntity.fqn == this.entity.fqn)
  }
  ```

### recursive: `Boolean`
Defines this relationship is recursive or not.
- **Code:**
  ```kotlin
  aggregate && (referenceEntity.fqn == entity.fqn)
  ```

### deprecated: `Boolean`
Defines this relationship is deprecated or not.
- **Default Value:**
  ```kotlin
  false
  ```

### examples: `List<Any>`
The examples of this relationship.
- **Default Value:**
  ```kotlin
  emptyList<Any>()
  ```

## Relationships

### entity: `Entity`
entity
- **Cardinality:** `1`

### mappings: `List<PropertyMapping>`
mappings
- **Cardinality:** `*`

### reference_entity: `Entity`
reference_entity
- **Cardinality:** `1`

### reverse: `Relationship?`
reverse
- **Cardinality:** `0..1`
- **Code:**
  ```kotlin
  referenceEntity.relationships.find{ it.name == reverseOf }
  ```