JSON Harvester Test Rig
================================================================================
This project provides a drop-in message queue receiver to test message output from projects sending to message queues. This project is based on Spring Integration.

This follows the model provided by harvester client, which means:
- config is provided/updated in groovy file

-  to run:
1. get zip file using maven or download
2. unzip
3. change into directory
4. chmod +x start-test-rig.sh
5. ./start-test-rig.sh

- to use harvester to send message:
1. change queue name to same as provided in test-rig's consumer config
2. change url (including portname) to same as provided in test-rig's amq config
