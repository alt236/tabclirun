#!/bin/bash
set -e
# set -x

cd "${0%/*}" # Ensure we are at the same dir as the script

./gradlew clean koverMergedReport