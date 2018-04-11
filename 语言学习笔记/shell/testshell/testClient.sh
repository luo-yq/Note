#!/bin/bash

./testParam.sh
echo $?
if [ $? == 0 ]
  then
  echo "success"
  else
  echo "failure"
fi



 