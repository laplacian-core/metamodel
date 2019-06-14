## **property_mapping** (*laplacian.metamodel.model.PropertyMapping*)
  property_mapping


---

### Properties
* **from:** `String`
  The from of this property_mapping.

* **to:** `String`
  The to of this property_mapping.

* **null_value:** `String?`
  The null_value of this property_mapping.


## Relationships
* **relationship:** `Relationship`
  relationship
* **property:** `Property`
  property

  **code:**
  ```kotlin
  relationship.entity.properties.find{ it.name == from }!!
  ```
* **reference_property:** `Property`
  reference_property

  **code:**
  ```kotlin
  relationship.referenceEntity.properties.find{ it.name == to }!!
  ```