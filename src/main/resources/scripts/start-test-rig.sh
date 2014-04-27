#!/bin/bash
export PROG_DIR=`cd \`dirname $0\`; pwd`
java -Denvironment=development -Dlog4j.debug=true -Xdebug -Dlog4j.configuration=file:///$PROG_DIR/log4j.xml -jar harvester-test-rig.jar config.groovy