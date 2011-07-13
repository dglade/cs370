#!/bin/bash

server=${1-localhost:8085}	#take the first parameter
 
response=`curl -s -H Content-Type:application/json -d '["one"]' http://$server/checkclearing`

if [ $response != '{"one":100}' ]; then
	echo $response
	echo "TEST FAILED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
	exit 1
else
	echo "Test succeeded"
fi
