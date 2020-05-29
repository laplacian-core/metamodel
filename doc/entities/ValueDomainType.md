# **ValueDomainType**
**namespace:** laplacian.metamodel

value_domain_type



---

## Properties

### name: `String`
The name of this value_domain_type.
- **Attributes:** *PK*

### namespace: `String`
The namespace of this value_domain_type.
- **Attributes:** *PK*

### type: `String`
The type of this value_domain_type.

### description: `String`
The description of this value_domain_type.
- **Default Value:**
  ```kotlin
  name
  ```

## Relationships

### domain: `ValueDomain`
domain
- **Cardinality:** `1`