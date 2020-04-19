<!-- @head-content@ -->
# laplacian/metamodel

A model that expresses the structure of relational model with aggregation support.
This model is used to define models from which templates generate resources such as source code or document.

<!-- @head-content@ -->

<!-- @toc@ -->
## Table of contents
1. [Usage](#usage)

1. [Schema model](#schema-model)

1. [Source list](#source-list)



<!-- @toc@ -->

<!-- @main-content@ -->
## Usage

Add the following entry to your project definition.
```yaml
project:
  models:
  - group: laplacian
    name: metamodel
    version: 1.0.0
```



## Schema model


### Model overview

The following diagram summarizes the structure of the model:
![](./doc/image/model-diagram.svg)


### Entities

- [**Entity**](<./doc/entities/Entity.md>)
  エンティティ
- [**NamedParam**](<./doc/entities/NamedParam.md>)
  named_param
- [**NamedValue**](<./doc/entities/NamedValue.md>)
  named_value
- [**Property**](<./doc/entities/Property.md>)
  property
- [**PropertyMapping**](<./doc/entities/PropertyMapping.md>)
  property_mapping
- [**Query**](<./doc/entities/Query.md>)
  The queries from which all navigation originates.
- [**Relationship**](<./doc/entities/Relationship.md>)
  relationship
- [**ValueDomainType**](<./doc/entities/ValueDomainType.md>)
  value_domain_type
- [**ValueDomain**](<./doc/entities/ValueDomain.md>)
  value_domain
- [**ValueItem**](<./doc/entities/ValueItem.md>)
  value_item




## Source list


[src/entities/value_domain_type.yml](<./src/entities/value_domain_type.yml>)

[src/entities/entity/example.yaml](<./src/entities/entity/example.yaml>)

[src/entities/query.yml](<./src/entities/query.yml>)

[src/entities/named_value.yml](<./src/entities/named_value.yml>)

[src/entities/value_domain.yml](<./src/entities/value_domain.yml>)

[src/entities/named_param.yml](<./src/entities/named_param.yml>)

[src/entities/property_mapping.yml](<./src/entities/property_mapping.yml>)

[src/entities/relationship.yml](<./src/entities/relationship.yml>)

[src/entities/property.yml](<./src/entities/property.yml>)

[src/entities/entity.yml](<./src/entities/entity.yml>)

[.editorconfig](<./.editorconfig>)

[.gitignore](<./.gitignore>)

[doc/image/model-diagram.svg](<./doc/image/model-diagram.svg>)

[doc/entities/Entity.md](<./doc/entities/Entity.md>)

[doc/entities/ValueDomainType.md](<./doc/entities/ValueDomainType.md>)

[doc/entities/Property.md](<./doc/entities/Property.md>)

[doc/entities/NamedValue.md](<./doc/entities/NamedValue.md>)

[doc/entities/ValueItem.md](<./doc/entities/ValueItem.md>)

[doc/entities/ValueDomain.md](<./doc/entities/ValueDomain.md>)

[doc/entities/Relationship.md](<./doc/entities/Relationship.md>)

[doc/entities/PropertyMapping.md](<./doc/entities/PropertyMapping.md>)

[doc/entities/NamedParam.md](<./doc/entities/NamedParam.md>)

[doc/entities/Query.md](<./doc/entities/Query.md>)

[README.md](<./README.md>)

[.gitattributes](<./.gitattributes>)

[model/project.yaml](<./model/project.yaml>)

[model/project/sources.yaml](<./model/project/sources.yaml>)





<!-- @main-content@ -->