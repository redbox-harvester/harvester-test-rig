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
		}
		harvest {
			pollRate = "5000"
			queueCapacity = "10"
			pollTimeout = "5000"
		}
	}
}