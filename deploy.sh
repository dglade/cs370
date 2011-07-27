#!/bin/bash

server=${1-localhost:8085}	#take the first parameter

function unknown_files {
  unknown_file_count=`git status --porcelain | grep "^??" | wc -l`
  [[ "$unknown_file_count" -gt 0 ]]
}

function uncommitted_changes {
  count=`git diff HEAD --shortstat | wc -l`
  [[ "$count" -gt 0 ]]
}

if unknown_files; then
  echo "Unknown files in project!"
  exit 1
fi

if uncommitted_changes; then
  echo "Uncommitted files in project!"
  exit 1
fi

gradle clean build
if [ "$?" -gt 0 ]; then
  exit 1
fi

gradle gaeRun &
server_pid=$!
if [ "$?" -gt 0 ]; then
  echo "Server fialed to start"
  exit 1
fi

server_status=1
echo -n "Waiting for local server to start..."
while [ ! server_status -gt 0 ]; do
  curl http://localhost:8085
  server_status=$?
  sleep 1
done

kill $server_pid

echo "Exiting..."
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