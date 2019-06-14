## **relationship** (*laplacian.metamodel.model.Relationship*)
  relationship


---

### Properties
* **name:** *PK* `String`
  The name of this relationship.

* **identifier:** `String?`
  The identifier of this relationship.

  **Default:**
  ```kotlin
  name.lowerUnderscorize()
  ```

* **cardinality:** `String`
  The cardinality of this relationship.

* **reference_entity_name:** `String`
  The reference_entity_name of this relationship.

* **aggregate:** `Boolean?`
  Defines this relationship is aggregate or not.

  **Default:**
  ```kotlin
  false
  ```

* **inherited:** `Boolean?`
  Defines this relationship is inherited or not.

  **Default:**
  ```kotlin
  false
  ```

* **reverse_of:** `String?`
  The reverse_of of this relationship.

* **description:** `String?`
  The description of this relationship.

  **Default:**
  ```kotlin
  name
  ```

* **snippet:** `String?`
  The snippet of this relationship.

* **class_name:** `String`
  The class_name of this relationship.

  **code:**
  ```kotlin
  if (multiple) "List<${referenceEntity.className}>" else (referenceEntity.className + if (nullable) "?" else "")
  ```

* **multiple:** `Boolean`
  Defines this relationship is multiple or not.

  **code:**
  ```kotlin
  cardinality.contains("""(\*|N|\.\.[2-9][0-9]+)""".toRegex())
  ```

* **allows_empty:** `Boolean`
  Defines this relationship is allows_empty or not.

  **code:**
  ```kotlin
  cardinality == "N" || cardinality == "*" || cardinality.contains("""(0\.\.)""".toRegex())
  ```

* **nullable:** `Boolean`
  Defines this relationship is nullable or not.

  **code:**
  ```kotlin
  !multiple && allowsEmpty
  ```

* **property_name:** `String`
  The property_name of this relationship.

  **code:**
  ```kotlin
  identifier.lowerCamelize()
  ```

* **bidirectional:** `Boolean`
  Defines this relationship is bidirectional or not.

  **code:**
  ```kotlin
  aggregate && referenceEntity.relationships.any {
      it.inherited && (it.referenceEntity.fqn == this.entity.fqn)
  }
  ```

* **recursive:** `Boolean`
  Defines this relationship is recursive or not.

  **code:**
  ```kotlin
  aggregate && (referenceEntity.fqn == entity.fqn)
  ```

* **deprecated:** `Boolean?`
  Defines this relationship is deprecated or not.

  **Default:**
  ```kotlin
  false
  ```

* **examples:** `List<Any>?`
  The examples of this relationship.

  **Default:**
  ```kotlin
  emptyList<Any>()
  ```


## Relationships
* **entity:** `Entity`
  entity
* **mappings:** `List<PropertyMapping>`
  mappings
* **reference_entity:** `Entity`
  reference_entity
* **reverse:** `Relationship?`
  reverse

  **code:**
  ```kotlin
  referenceEntity.relationships.find{ it.name == reverseOf }
  ```