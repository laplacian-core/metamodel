#!/usr/bin/env bash
set -e
PROJECT_BASE_DIR=$(cd $"${BASH_SOURCE%/*}/../" && pwd)

SCRIPT_BASE_DIR="$PROJECT_BASE_DIR/scripts"

LOCAL_REPO_PATH="$PROJECT_BASE_DIR/../../../mvn-repo"

OPT_NAMES='hv-:'

ARGS=
HELP=
VERBOSE=


run_publish_local_metamodel_plugin() {
  parse_args "$@"
  ! [ -z $VERBOSE ] && set -x
  ! [ -z $HELP ] && show_usage && exit 0
  main
}

parse_args() {
  while getopts $OPT_NAMES OPTION;
  do
    case $OPTION in
    -)
      case $OPTARG in
      help)
        HELP='yes';;
      verbose)
        VERBOSE='yes';;
      *)
        echo "ERROR: Unknown OPTION --$OPTARG" >&2
        exit 1
      esac
      ;;
    h) HELP='yes';;
    v) VERBOSE='yes';;
    esac
  done
  ARGS=$@
}

show_usage () {
cat << END
Usage: $(basename "$0") [OPTION]...
  -h, --help
    Displays how to use this command.
  -v, --verbose
    Displays more detailed command execution information.
END
}

source $SCRIPT_BASE_DIR/.publish-local-metamodel-plugin/main.sh
run_publish_local_metamodel_plugin "$@"