#!/bin/bash

PIPELINE_NAME=${1:-test-pipeline}
ALIAS=${2:-docker}
CREDENTIALS=${3:-credentials.yml}

echo y | fly -t "${ALIAS}" sp -p "${PIPELINE_NAME}" -c pipeline.yml -l "${CREDENTIALS}"
