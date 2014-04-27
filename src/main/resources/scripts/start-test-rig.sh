#!/bin/bash
export PROG_DIR=`cd \`dirname $0\`; pwd`
export JMX_OPTS="-Dcom.sun.management.jmxremote.port=9019 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
java -Denvironment=development -Dlog4j.debug=true -Xdebug -Dlog4j.configuration=file:///$PROG_DIR/log4j.xml ${JMX_OPTS} -jar harvester-test-rig.jar config.groovy