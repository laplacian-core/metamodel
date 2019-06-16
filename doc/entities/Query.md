# **Query**
**namespace:** laplacian.metamodel

The queries from which all navigation originates.




---

## Properties

### name: `String`
クエリ名称

### identifier: `String`
識別子
- **Defaul value:**
  ```kotlin
  name.lowerUnderscorize()
  ```

### type: `String`
結果型
- **Defaul value:**
  ```kotlin
  resultEntity?.className?.let { className ->
      if (cardinality.contains("*")) "List<$className>" else className
  }
  ```

### result_entity_name: `String`
クエリ結果エンティティ名

### description: `String`
詳細
- **Defaul value:**
  ```kotlin
  name
  ```

### cardinality: `String`
多重度
- **Defaul value:**
  ```kotlin
  "*"
  ```

### snippet: `String`
クエリスクリプト

### oneliner: `Boolean`
Defines this query is oneliner or not.
- **Code:**
  ```kotlin
  !snippet.contains("""\breturn\b""".toRegex())
  ```

### deprecated: `Boolean`
Defines this query is deprecated or not.
- **Defaul value:**
  ```kotlin
  false
  ```

## Relationships

### entity: `Entity`
エンティティ
- **Cardinality:** `1`

### result_entity: `Entity?`
クエリ結果エンティティ
- **Cardinality:** `0..1`