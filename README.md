# model.metamodel

A model that expresses the structure of relational model with aggregation support.
This model is used to define models from which templates generate resources such as source code or document.


## Model overview

The following diagram summarizes the structure of the model:

![](./doc/image/model-diagram.svg)

## Entities
### [Entity](./doc/entities/Entity.md)

エンティティ

### [NamedParam](./doc/entities/NamedParam.md)

named_param

### [NamedValue](./doc/entities/NamedValue.md)

named_value

### [Property](./doc/entities/Property.md)

property

### [PropertyMapping](./doc/entities/PropertyMapping.md)

property_mapping

### [Query](./doc/entities/Query.md)

The queries from which all navigation originates.


### [Relationship](./doc/entities/Relationship.md)

relationship

### [ValueDomainType](./doc/entities/ValueDomainType.md)

value_domain_type

### [ValueDomain](./doc/entities/ValueDomain.md)

value_domain

### [ValueItem](./doc/entities/ValueItem.md)

value_item



## Usage

Add the following entry to your project definition.

```yaml
project:
  models:
  - group: laplacian
    name: model.metamodel
    version: 1.0.0
```