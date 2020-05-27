#!/usr/bin/env bash
set -e
PROJECT_BASE_DIR=$(cd $"${BASH_SOURCE%/*}/../" && pwd)

SCRIPT_BASE_DIR="$PROJECT_BASE_DIR/scripts"

LOCAL_REPO_PATH="$PROJECT_BASE_DIR/../../../mvn-repo"

OPT_NAMES='hvc-:'

ARGS=
HELP=
VERBOSE=
CONTINUE_ON_ERROR=


run_generate_all() {
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
      continue-on-error)
        CONTINUE_ON_ERROR='yes';;
      *)
        echo "ERROR: Unknown OPTION --$OPTARG" >&2
        exit 1
      esac
      ;;
    h) HELP='yes';;
    v) VERBOSE='yes';;
    c) CONTINUE_ON_ERROR='yes';;
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
  -c, --continue-on-error
    Even if the given command fails in a subproject in the middle, executes it for the remaining subprojects.
END
}

source $SCRIPT_BASE_DIR/.generate-all/main.sh
run_generate_all "$@"