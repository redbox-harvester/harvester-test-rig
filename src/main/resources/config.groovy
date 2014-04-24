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
			inboundAdapter = "channelIn"
			url = "tcp://localhost:9501"
		}
		harvest {
			pollRate = "5000"
			queueCapacity = "10"
			pollTimeout = "5000"
			scripts {
				//             "script path" : "configuration path" - pass in an emtpy string config path if you do not want to override the script's default config lookup behavior.
				preBuild = [] // executed after a successful, but prior to building the JSON String, no data is passed
				preAssemble = [] // executed prior to building the JSON string, each resultset (map) of the JDBC poll is passed as 'data'
				postBuild = [] // executed after the data is processed, but prior to ending the poll
			}
		}
	}
	production {
		file {
			base = ""
			runtimePath = file.base+"runtimeP/harvester-config-console.groovy"
			customPath = file.base+"customP/harvester-config-console.groovy"
			siPath = "applicationContext-si.xml"
			ignoreEntriesOnSave = ["runtime"]
		}
		activemq {
			inboundAdapter = "channel_in"
			url = "tcp://localhost:9201"
		}
		harvest {
			pollRate = "5000"
			queueCapacity = "10"
			pollTimeout = "5000"
		}
	}
}