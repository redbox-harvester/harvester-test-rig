environments {
	development {
		file {
			base = ""
			runtimePath = file.base+"runtime/harvester-config-console.groovy"
			customPath = file.base+"custom/harvester-config-console.groovy"
			siPath = "applicationContext-si.xml"
			ignoreEntriesOnSave = ["runtime"]
		}
		activemq {
			url = "tcp://localhost:9222"
            dataDirectory = file.base + "activemq-data"
            queueName = "test"
		}
		consumer {
			pollRate = "5000"
			queueCapacity = "10"
			pollTimeout = "5000"
            outputFile = "test-rig-output.txt"
            sessionCacheSize = "10"
		}
	}
}