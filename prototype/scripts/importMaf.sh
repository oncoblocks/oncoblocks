mvn -e -q -f ../pom.xml exec:java -Dexec.mainClass="org.oncoblocks.data_block.scripts.ImportMutationData" -Dexec.args="$1" -Dexec.cleanupDaemonThreads=false
