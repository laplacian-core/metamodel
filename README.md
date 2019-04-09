# laplacian:model:metamodel

A model that expresses the structure of relational model with aggregation support.
This model is used to define models from which templates generate resources.



## The structure of the *metamodel* model

The following diagram summarizes the structure of the model:

![](./doc/image/model-diagram.svg)


As shown in above diagram, this model depends on the following external models.

- *metamodel* (laplacian.model.metamodel)



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

Then put some template files under the *./template* directory and type the following command to generate files:
```bash
./gradlew lG
```