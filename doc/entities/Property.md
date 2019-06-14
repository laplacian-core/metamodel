## **property** (*laplacian.metamodel.model.Property*)
  property


---

### Properties
* **name:** *PK* `String`
  The name of this property.

* **identifier:** `String?`
  The identifier of this property.

  **Default:**
  ```kotlin
  name.lowerUnderscorize()
  ```

* **primary_key:** `Boolean?`
  Defines this property is primary_key or not.

  **Default:**
  ```kotlin
  false
  ```

* **subtype_key:** `Boolean?`
  Defines this property is subtype_key or not.

  **Default:**
  ```kotlin
  false
  ```

* **type:** `String`
  The type of this property.

* **domain_type_name:** `String?`
  制約型名

* **size:** `Int?`
  the maximum allowed size of the content of this property

  **Default:**
  ```kotlin
  when(type) {
      "string" -> 200
      else -> throw IllegalStateException(
          "The size of $type type field can not be defined."
      )
  }
  ```

* **optional:** `Boolean?`
  Defines this property is optional or not.

  **Default:**
  ```kotlin
  false
  ```

* **description:** `String?`
  The description of this property.

  **Default:**
  ```kotlin
  if (type == "boolean") "Defines this ${entity.name} is $name or not." else "The $name of this ${entity.name}."
  ```

* **default_value:** `String?`
  The default value of this property, which is used when the actual value is null

* **example_values:** `List<Any>?`
  The example_values of this property.

  **Default:**
  ```kotlin
  emptyList<Any>()
  ```

* **table_column_name:** `String?`
  The table_column_name of this property.

  **Default:**
  ```kotlin
  identifier.lowerUnderscorize()
  ```

* **snippet:** `String?`
  The snippet of this property.

* **multiple:** `Boolean?`
  Defines this property is multiple or not.

  **Default:**
  ```kotlin
  false
  ```

* **property_name:** `String`
  The property_name of this property.

  **code:**
  ```kotlin
  identifier.lowerCamelize()
  ```

* **class_name:** `String`
  The class_name of this property.

  **code:**
  ```kotlin
  (if (type == "number") "Int" else type.upperCamelize()).let {
      if (multiple) "List<$it>" else it
  }
  ```

* **nullable:** `Boolean`
  Whether this property permit a null value

  **code:**
  ```kotlin
  optional && (defaultValue == null)
  ```

* **overwrites:** `Boolean`
  Whether this preoperty overwrite a property which is defined in supertype.

  **code:**
  ```kotlin
  entity.ancestors.any{ s -> s.properties.any{ p -> p.name == name }}
  ```

* **deprecated:** `Boolean?`
  Defines this property is deprecated or not.

  **Default:**
  ```kotlin
  false
  ```


## Relationships
* **entity:** `Entity`
  entity
* **domain:** `ValueDomain?`
  domain
* **domain_type:** `ValueDomainType?`
  domain_type