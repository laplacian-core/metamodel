#!/usr/bin/env bash
set -e
SCRIPT_BASE_DIR=$(cd $"${BASH_SOURCE%/*}" && pwd)
PROJECT_BASE_DIR=$(cd $SCRIPT_BASE_DIR && cd .. && pwd)

LOCAL_REPO_PATH="$PROJECT_BASE_DIR/../mvn-repo"

if [[ -d './subprojects/mvn-repo' ]]
then
  LOCAL_REPO_PATH='./subprojects/mvn-repo'
fi

${SCRIPT_BASE_DIR}/laplacian-generate.sh \
  --plugin 'laplacian:laplacian.project.schema-plugin:1.0.0' \
  --template 'laplacian:laplacian.project.base-template:1.0.0' \
  --model-files './model/project.yaml' \
  --model-files './model/project' \
  --target-dir './' \
  --local-repo "$LOCAL_REPO_PATH"
