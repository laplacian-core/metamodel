#!/usr/bin/env bash
set -e
PROJECT_BASE_DIR=$(cd $"${BASH_SOURCE%/*}/../" && pwd)

SCRIPT_BASE_DIR="$PROJECT_BASE_DIR/scripts"
LOG_FILE="./update-subprojects-$(date +%Y-%m-%dT%H%M%S).log"
SUBPROJECTS='laplacian-metamodel-plugin
'

for subproject in $SUBPROJECTS
do
  UPDATE_SCRIPT="./scripts/$subproject/update-project.sh"
  if [[ -f $UPDATE_SCRIPT ]]
  echo "Updating $subproject..."
  then
    $UPDATE_SCRIPT > $LOG_FILE
  fi
  echo "...Done"
done