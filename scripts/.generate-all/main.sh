#!/usr/bin/env bash

SCRIPTS='generate-laplacian-metamodel-plugin
'

main() {
  $PROJECT_BASE_DIR/scripts/generate
  for script in $SCRIPTS
  do
    echo "
    === $script ===
    "
    $PROJECT_BASE_DIR/scripts/$script
  done
}