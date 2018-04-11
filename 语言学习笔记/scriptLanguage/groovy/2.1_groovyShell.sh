#!/bin/bash
groovy_home=/Users/lyt1987/Desktop/mypcsoft/groovy-2.4.3
export PATH=$PATH:$groovy_home/bin
export groovy_home

groovy
groovy testGroovy/src/ClassTest.groovy
groovy testGroovy/src/scriptTest.groovy 