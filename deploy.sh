#!/bin/bash

server=${1-localhost:8085}	#take the first parameter

function unknown_files {
  unknown_file_count=`git status --porcelain | grep "^??" | wc -l`
  [[ "$unknown_file_count" -gt 0 ]]
}

function uncommitted_changes {
  uncommited_change_count=`git diff HEAD --shortstat | wc -l`
  [[ "$changes_committed" == "" ]]
}

if unknown_files; then
  echo "Unknown files in project!"
  exit 1
fi

if uncommitted_changes; then
  echo "Uncommitted files in project!"
  exit 1
fi

ehco "Exiting..."
exit 0
 

#curl -s -H Content-Type:application/json -d '["one"]' http://$server/checkclearing

#history=`curl http://$server/checkclearing`
#response=`curl -s -H Content-Type:application/json -d "$history" http://$server/checkclearing`

#if [ "$response" != '{"one":100}' ]; then
#	echo $response
#	echo "TEST FAILED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
#	exit 1
#else
#	echo "Test succeeded"
#fi