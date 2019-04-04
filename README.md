# laplacian:model:metamodel

This model expresses the structure of models. In other words, this model explains
how to define "a model".


## The structure of the *metamodel* model

The following diagram summarizes the structure of the model:

![](./doc/image/model-diagram.svg)


This model does not have any dependency to external models.


## Getting started

Firstly, you need to add the following entry to your `laplacian-module.yml` :

```yaml
project:
  group: ${your.project.group}
  name: ${your.project.name}
  type: project
  version: "1.0.0"
  models:
  ## ↓↓ ADD ↓↓ ##
  - group: laplacian
    name: metamodel
    version: "1.0.0"
  ## ↑↑ ADD ↑↑ ##
```

To reflect the change, you need to type the following command in your console :
```bash
./gradlew lM
```