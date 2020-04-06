#!/usr/bin/env bash
set -e
SCRIPT_BASE_DIR=$(cd $"${BASH_SOURCE%/*}" && pwd)
PROJECT_BASE_DIR=$(cd $SCRIPT_BASE_DIR && cd .. && pwd)

GENERATOR_SCRIPT_FILE_NAME=laplacian-model-metamodel-generate.sh
TARGET_SCRIPT_DIR="$TARGET_PROJECT_DIR/scripts"
TARGET_PROJECT_GENERATOR_SCRIPT="$TARGET_SCRIPT_DIR/$GENERATOR_SCRIPT_FILE_NAME"

normalize_path () {
  local path=$1
  if [[ $path == /* ]]
  then
    echo $path
  else
    echo "${PROJECT_BASE_DIR}/$path"
  fi
}

#
# Generate resources for model.metamodel project.
#
${SCRIPT_BASE_DIR}/laplacian-generate.sh \
  --schema 'laplacian:laplacian.schema.metamodel:1.0.0' \
  --template 'laplacian:laplacian.template.metamodel.document:1.0.0' \
  --model 'laplacian:laplacian.model.metamodel:1.0.0' \
  --model-files './model/project.yaml' \
  --target-dir ./