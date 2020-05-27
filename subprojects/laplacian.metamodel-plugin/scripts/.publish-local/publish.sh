#!/usr/bin/env bash

DEST_DIR="$PROJECT_BASE_DIR/dest"

publish() {
  (cd $DEST_DIR
    ./gradlew publish
  )
}