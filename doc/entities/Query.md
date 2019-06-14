## **query** (*laplacian.metamodel.model.Query*)
  The queries from which all navigation originates.


---

### Properties
* **name:** `String`
  クエリ名称

* **identifier:** `String?`
  識別子

  **Default:**
  ```kotlin
  name.lowerUnderscorize()
  ```

* **type:** `String?`
  結果型

  **Default:**
  ```kotlin
  resultEntity?.className?.let { className ->
      if (cardinality.contains("*")) "List<$className>" else className
  }
  ```

* **result_entity_name:** `String?`
  クエリ結果エンティティ名

* **description:** `String?`
  詳細

  **Default:**
  ```kotlin
  name
  ```

* **cardinality:** `String?`
  多重度

  **Default:**
  ```kotlin
  "*"
  ```

* **snippet:** `String`
  クエリスクリプト

* **oneliner:** `Boolean`
  Defines this query is oneliner or not.

  **code:**
  ```kotlin
  !snippet.contains("""\breturn\b""".toRegex())
  ```

* **deprecated:** `Boolean?`
  Defines this query is deprecated or not.

  **Default:**
  ```kotlin
  false
  ```


## Relationships
* **entity:** `Entity`
  エンティティ
* **result_entity:** `Entity?`
  クエリ結果エンティティ