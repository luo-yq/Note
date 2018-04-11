#!/bin/bash


if [ $# == 0 ]
   then
   echo "you can use deploy or test "
   exit 0
fi
if [ $1 == "deploy" ]
    then 
    echo "deploy"
    elif [ $1 == "test" ]
    then
    echo "test"
    else
    echo "use no param to see"
fi
