./resetDb.sh
./importGenes.sh ../data/human_genes.txt
./importCancerStudies.sh ../data/TCGA_codes.txt
mvn -e -q -f ../pom.xml exec:java -Dexec.mainClass="org.oncoblocks.data_block.scripts.ImportSimulatedData" -Dexec.args="$1"
