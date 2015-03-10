package org.oncoblocks.data_block.servlet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.oncoblocks.data_block.model.GenomicProfile;
import org.oncoblocks.data_block.model.Mutation;
import org.oncoblocks.data_block.model.OncoPanel;
import org.oncoblocks.data_block.model.Patient;
import org.oncoblocks.data_block.model.PatientBundle;
import org.oncoblocks.data_block.model.Treatment;

/**
 * Creates Simulated Data for the Het Viewer Prototype.
 *
 */
public class HetViewSimulator {
	private static Random randomGenerator = new Random();

	public PatientBundle getPatientBundle() {
		Patient patient = new Patient();
		patient.setCancerType("ovarian");
		patient.setHistologicalSubtype(getRandomSubType());
		patient.setAge(50 + randomGenerator.nextInt(30));
		patient.setTumorGrade(getRandomTumorGrade());
		patient.setTumorStage(getRandomTumorStage());
		PatientBundle patientBundle = new PatientBundle();
		
		int randomYear = 2010 + randomGenerator.nextInt(4);
		ArrayList<Treatment> treatmentList = generateRandomTreatments(randomYear);
		ArrayList <GenomicProfile> profileList = generateRandomProfiles(randomYear);
		
		patientBundle.setPatient(patient);
		patientBundle.setTreatmentList(treatmentList);
		patientBundle.setGenomicProfileList(profileList);
		return patientBundle;
	}

	private ArrayList <GenomicProfile> generateRandomProfiles(int randomYear) {
		ArrayList <GenomicProfile> profileList = new ArrayList<GenomicProfile>();
		profileList.add(generateRandomProfile(randomYear, 1, 2));
		profileList.add(generateRandomProfile(randomYear, 4, 28));
		profileList.add(generateRandomProfile(randomYear, 8, 28));
		return profileList;
	}

	private GenomicProfile generateRandomProfile(int randomYear, int month, int day) {
		GenomicProfile genomicProfile = new GenomicProfile();
		genomicProfile.setTissueType("ovarian");
		genomicProfile.setTumorType("primary");
		Calendar profileDate = Calendar.getInstance();
		profileDate.set(randomYear, month, day);
		genomicProfile.setDateOfBiopsy(profileDate.getTime());
		
		ArrayList <Mutation> mutationList = new ArrayList<Mutation>();
		OncoPanel oncoPanel = new OncoPanel();
		ArrayList <String> geneList = oncoPanel.getGeneList();
		for (int i=0; i<50; i++) {
			Mutation mutation = new Mutation();
			String geneSymbol = geneList.get(randomGenerator.nextInt(geneList.size()));
			mutation.setGeneSymbol(geneSymbol);
	        mutation.setEntrezGeneId(-1);
	        mutation.setAaChange("V600_" + randomGenerator.nextInt(20000));
	        mutation.setAlternativeAlleleReads(randomGenerator.nextInt(300));
	        mutation.setAnnotationTranscript("transcript_"+ randomGenerator.nextInt(20000));
	        mutation.setcDnaChange("dna_change_" + randomGenerator.nextInt(20000));
	        mutation.setChromosome("chr" + randomGenerator.nextInt(26));
	        mutation.setCodonChange("codon_change_" + randomGenerator.nextInt(20000));
	        mutation.setDbSnpRsId("db_snp_id" + randomGenerator.nextInt(20000));
	        mutation.setDbSnpRsValStatus("db_rs_" + randomGenerator.nextInt(20000));
	        mutation.setDbSnpRsValStatus("db_snp_val_" + randomGenerator.nextInt(20000));
	        mutation.setDnaEndPosition(randomGenerator.nextInt(20000));
	        mutation.setDnaStartPosition(randomGenerator.nextInt(20000));
	        mutation.setOtherTranscript("other_" + randomGenerator.nextInt(20000));
	        mutation.setReferenceAllele("ref_" + randomGenerator.nextInt(20000));
	        mutation.setReferenceAlleleReads(randomGenerator.nextInt(300));
	        mutation.setReferenceGenome("hg17");
	        mutation.setRefseqMrnaId("mrna_" + randomGenerator.nextInt(20000));
	        mutation.setRefseqProtId("ref_seq_" + randomGenerator.nextInt(20000));
	        mutation.setStrand("strand_" + randomGenerator.nextInt(2));
	        mutation.setSwissprotAccession("swiss_prot_" + randomGenerator.nextInt(20000));
	        mutation.setSwissprotEntry("swiss_prot_entry_" + randomGenerator.nextInt(20000));
	        mutation.setTranscriptStrand("transcript_strand_" + randomGenerator.nextInt(2));
	        mutation.setUniprotAaPosition("uniprot_aa_" + randomGenerator.nextInt(20000));
	        mutation.setUniprotRegion("region_" + randomGenerator.nextInt(20000));
	        mutation.setUniprotSite("site_" + randomGenerator.nextInt(20000));
	        mutation.setVariantAllele("variant_allele_"+ randomGenerator.nextInt(20000));
	        mutation.setVariantClassification("variant_classification_" + randomGenerator.nextInt(25));
	        mutation.setVertebrateAaAlignment("align_" + randomGenerator.nextInt(20000));

			mutationList.add(mutation);
		}
		genomicProfile.setMutationList(mutationList);
		return genomicProfile;
	}

	private ArrayList<Treatment> generateRandomTreatments(int randomYear) {
		ArrayList<Treatment> treatmentList = new ArrayList<Treatment>();
		treatmentList.add(createTreatment("treatment a", randomYear, 1, 15, 4, 25));
		treatmentList.add(createTreatment("treatment b", randomYear, 3, 15, 5, 25));
		treatmentList.add(createTreatment("treatment c", randomYear, 6, 15, 8, 25));
		return treatmentList;
	}
	
	private Treatment createTreatment(String treatmentName, int year, int startMonth, int startDate, 
			int endMonth, int endDate) {
		Treatment treatment = new Treatment();
		treatment.setTreatmentName(treatmentName);
		treatment.setTreatmentDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Nullam facilisis turpis quis turpis accumsan, consequat auctor odio auctor. "
				+ "Curabitur aliquet gravida odio vitae finibus. Phasellus ut volutpat sapien, "
				+ "id eleifend sem. Donec vel ex dui. Quisque accumsan congue nibh at aliquet.");
		Calendar startA = Calendar.getInstance();
		startA.set(year,  startMonth, startDate);
		Calendar endA = Calendar.getInstance();
		endA.set(year,  endMonth, endDate);
		treatment.setStartDate(startA.getTime());
		treatment.setEndDate(endA.getTime());
		return treatment;
	}
	
	public String getRandomSubType() {
		ArrayList<String> subTypeList = new ArrayList<String>();
		subTypeList.add("serous");
		subTypeList.add("clear cell");
		subTypeList.add("mucinous");
		int randomIndex = randomGenerator.nextInt(3);
		return subTypeList.get(randomIndex);
	}
	
	public String getRandomTumorGrade() {
		return "Grade " + (randomGenerator.nextInt(3) + 1);
	}
	
	public String getRandomTumorStage() {
		ArrayList<String> stageList = new ArrayList<String>();
		stageList.add("I");
		stageList.add("II");
		stageList.add("III");
		stageList.add("IV");
		int randomIndex = randomGenerator.nextInt(4);
		return "Stage " + stageList.get(randomIndex);
	}	
}
