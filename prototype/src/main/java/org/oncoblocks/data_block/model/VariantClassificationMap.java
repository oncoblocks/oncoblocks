package org.oncoblocks.data_block.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Variant Classification Map.
 * <p/>
 * In the comments below, SO refers to Sequence Ontology.
 * <p/>
 * SO Terms can be accessed like so:
 * <p/>
 * http://www.sequenceontology.org/miso/current_release/term/SO:000XXXX
 */
public class VariantClassificationMap {

    /**
     * A sequence variant whereby at least one base of a codon is changed,
     * resulting in a premature stop codon, leading to a shortened transcript.
     * <p/>
     * Equivalent to:  stop_gained (SO:0001587).
     */
    public final static String NONSENSE_MUTATION = "Nonsense_Mutation";

    /**
     * A sequence variant in which a change has occurred within the region
     * of the splice site, either within 1-3 bases of the exon or 3-8 bases of the intron.
     * <p/>
     * Equivalent to:  splice_region_variant (SO:0001630)
     */
    public final static String SPLICE_SITE = "Splice_Site";

    /**
     * Mutation in the start codon (usually AUG).
     * <p/>
     * Equivalent to:  initiator_codon_variant (SO:0001582), a codon variant
     * that changes at least one base of the first codon of a transcript
     */
    public final static String TRANSLATION_START_SITE = "Translation_Start_Site";

    /**
     * A sequence variant, where the change may be longer than 3 bases,
     * and at least one base of a codon is changed resulting in a codon that
     * encodes for a different amino acid.
     * <p/>
     * Equivalent to:  missense_variant (SO:0001583).
     */
    public final static String MISSENSE_MUTATION = "Missense_Mutation";

    /**
     * A sequence variant where there is no resulting change to the encoded amino acid.
     * <p/>
     * Equivalent to:  synonymous_variant (SO:0001819).
     */
    public final static String SILENT = "Silent";

    /**
     * A sequence variant where at least one base of the terminator codon
     * (stop) is changed, resulting in an elongated transcript.
     * <p/>
     * Equivalent to:  stop_lost (SO:0001578).
     */
    public final static String NONSTOP_MUTATION = "Nonstop_Mutation";

    /**
     * An inframe non synonymous variant that inserts bases into
     * in the coding sequence, e.g. the number of inserted nucleotides is
     * divisible by three.
     * <p/>
     * Equivalent to:  inframe_insertion (SO:0001821).
     */
    public final static String IN_FRAME_INS = "In_Frame_Ins";

    /**
     * An inframe non synonymous variant that deletes bases from the coding sequence.
     * e.g. the number of deleted based is divisible by three.
     * <p/>
     * Equivalent to:  inframe_deletion (SO:0001822).
     */
    public final static String IN_FRAME_DEL = "In_Frame_Del";

    /**
     * A sequence variant which causes a disruption of the translational
     * reading frame, because the number of nucleotides inserted is not a
     * multiple of three.
     * <p/>
     * Equivalent to:  frameshift_variant (SO:0001589).
     */
    public final static String FRAME_SHIFT_INS = "Frame_Shift_Ins";

    /**
     * A sequence variant which causes a disruption of the translational
     * reading frame, because the number of nucleotides deletion is not a
     * multiple of three.
     * <p/>
     * Equivalent to:  frameshift_variant (SO:0001589).
     */
    public final static String FRAME_SHIFT_DEL = "Frame_Shift_Del";

    /**
     * A UTR variant of the 3' UTR.
     * <p/>
     * Equivalent to:  3_prime_UTR_variant (SO:0001624).
     */
    public final static String THREE_PRIME_UTR = "3'UTR";

    /**
     * A UTR variant of the 5' UTR.
     * <p/>
     * Equivalent to:  5_prime_UTR_variant (SO:0001623).
     */
    public final static String FIVE_PRIME_UTR = "5'UTR";

    /**
     * Variant in the region of DNA which borders the 3' end of a
     * transcription unit and where a variety of regulatory sequences
     * are located.
     * <p/>
     * Equivalent to:  downstream_gene_variant (SO:000163)
     */
    public final static String THREE_PRIME_FLANK = "3'Flank";

    /**
     * Variant in the region of DNA which borders the 5' end of a
     * transcription unit and where a variety of regulatory sequences
     * are located.
     * <p/>
     * Equivalent to:  upstream_gene_variant (SO:0001631).
     */
    public final static String FIVE_PRIME_FLANK = "5'Flank";

    /**
     * A sequence variant located in the intergenic region, between genes.
     * <p/>
     * Equivalent to:  intergenic_variant (SO:0001628).
     */
    public final static String IGR = "IGR";

    /**
     * A transcript variant occurring within an intron.
     * <p/>
     * Equivalent to:  intron_variant (SO:0001627).
     */
    public final static String INTRON = "Intron";

    /**
     * A transcript variant of a non coding RNA.
     * <p/>
     * Equivalent:  nc_transcript_variant (SO:0001619).
     */
    public final static String RNA = "RNA";

    /**
     * Not Available.
     */
    public final static String NA = "NA";

    /**
     * Any form of insertion of deletion, in-frame or out of frame.
     */
    public final static String INDEL = "In_Del";

    private static VariantClassificationMap variantClassificationMap;
    private Set controlledTermSet = new HashSet<String>();
    private HashMap<String, String> termMap = new HashMap<String, String>();

    /**
     * Gets the Singleton Object.
     *
     * @return Variant Classification Map Singleton Object.
     */
    public static VariantClassificationMap getInstance() {
        if (variantClassificationMap == null) {
            variantClassificationMap = new VariantClassificationMap();
        }
        return variantClassificationMap;
    }

    /**
     * Private Constructor to Ensure Singleton Pattern.
     */
    private VariantClassificationMap() {
        initControlledTerms();
        initTermMap();
    }

    private void initTermMap() {
        termMap.put("Synonymous", SILENT);

        termMap.put("Nonsense", NONSENSE_MUTATION);
        termMap.put("STOP_GAINED", NONSENSE_MUTATION);

        termMap.put("Missense", MISSENSE_MUTATION);
        termMap.put("NON_SYNONYMOUS_CODING", MISSENSE_MUTATION);

        termMap.put("Read-through", NONSTOP_MUTATION);
        termMap.put("Stop_Codon_Del", NONSTOP_MUTATION);
        termMap.put("Stop lost", NONSTOP_MUTATION);
        termMap.put("STOP_LOST", NONSTOP_MUTATION);

        termMap.put("In-frame del", IN_FRAME_DEL);
        termMap.put("In_frame_Del", IN_FRAME_DEL);
        termMap.put("In-frame ins", IN_FRAME_INS);
        termMap.put("In_frame_Ins", IN_FRAME_INS);
        termMap.put("Indel", INDEL);

        termMap.put("Splice_Site_Del", SPLICE_SITE);
        termMap.put("Splice_Site_Ins", SPLICE_SITE);
        termMap.put("Splice_Site_SNP", SPLICE_SITE);
        termMap.put("Essential splice ins", SPLICE_SITE);
        termMap.put("Essential Splice del", SPLICE_SITE);
        termMap.put("Essential splice del", SPLICE_SITE);
        termMap.put("Essential splice", SPLICE_SITE);
        termMap.put("ESSENTIAL_SPLICE_SITE", SPLICE_SITE);
        termMap.put("Splice_Site_ONP", SPLICE_SITE);
        termMap.put("Splice_Site_DNP", SPLICE_SITE);
        termMap.put("Splice_Region", SPLICE_SITE);
        termMap.put("Splice site", SPLICE_SITE);
        termMap.put("SPLICE_SITE", SPLICE_SITE);

        termMap.put("FRAMESHIFT_CODING", FRAME_SHIFT_DEL);
        termMap.put("Frameshift del", FRAME_SHIFT_DEL);
        termMap.put("Frameshift ins", FRAME_SHIFT_INS);

        termMap.put("Non-coding_Transcript", RNA);

        termMap.put("De_novo_Start_InFrame", TRANSLATION_START_SITE);
        termMap.put("De_novo_Start_OutOfFrame", TRANSLATION_START_SITE);
        termMap.put("Start_Codon_Ins", TRANSLATION_START_SITE);
        termMap.put("Start_Codon_Del", TRANSLATION_START_SITE);
        termMap.put("Start_Codon_DNP", TRANSLATION_START_SITE);
        initSangerMappings();
        initEVPMappings();
    }

    /**
     * Add Mappings from the Ensembl Variant Effect Predictor.
     */
    private void initEVPMappings() {
        termMap.put("stop_gained", NONSENSE_MUTATION);
        termMap.put("splice_region_variant", SPLICE_SITE);
        termMap.put("initiator_codon_variant", TRANSLATION_START_SITE);
        termMap.put("missense_variant", MISSENSE_MUTATION);
        termMap.put("synonymous_variant", SILENT);
        termMap.put("stop_lost", NONSTOP_MUTATION);
        termMap.put("inframe_insertion", IN_FRAME_INS);
        termMap.put("inframe_deletion", IN_FRAME_DEL);
        termMap.put("frameshift_variant", INDEL);
        termMap.put("3_prime_UTR_variant", THREE_PRIME_UTR);
        termMap.put("5_prime_UTR_variant", FIVE_PRIME_UTR);
        termMap.put("downstream_gene_variant", THREE_PRIME_FLANK);
        termMap.put("upstream_gene_variant", FIVE_PRIME_FLANK);
        termMap.put("intron_variant", INTRON);
        termMap.put("nc_transcript_variant", RNA);
        termMap.put("stop_retained_variant", SILENT);
        termMap.put("splice_donor_variant", SPLICE_SITE);


        //  Old VEP Terms
        termMap.put("synonymous_codon", SILENT);
        termMap.put("non_synonymous_codon", MISSENSE_MUTATION);
        termMap.put("splice_acceptor_variant", SPLICE_SITE);
    }

    /**
     * Add Mappings Observed in MAF Files Derived from the Sanger Institute.
     */
    private void initSangerMappings() {
        termMap.put("splice", SPLICE_SITE);
        termMap.put("intron_exon", SPLICE_SITE);
        termMap.put("silent", SILENT);
        termMap.put("frame_shift_del", FRAME_SHIFT_DEL);
        termMap.put("frame_shift_ins", FRAME_SHIFT_INS);
        termMap.put("in_frame_del", IN_FRAME_DEL);
        termMap.put("in_frame_ins", IN_FRAME_INS);
        termMap.put("nonsense", NONSENSE_MUTATION);
        termMap.put("missense", MISSENSE_MUTATION);
        termMap.put("nonstop", NONSTOP_MUTATION);
        termMap.put("in_frame_ins", IN_FRAME_INS);
    }

    /**
     * Inits the Complete Set of Controlled Vocabulary Terms.
     */
    private void initControlledTerms() {
        controlledTermSet.add(NONSENSE_MUTATION);
        controlledTermSet.add(SPLICE_SITE);
        controlledTermSet.add(TRANSLATION_START_SITE);
        controlledTermSet.add(MISSENSE_MUTATION);
        controlledTermSet.add(SILENT);
        controlledTermSet.add(NONSTOP_MUTATION);
        controlledTermSet.add(IN_FRAME_INS);
        controlledTermSet.add(IN_FRAME_DEL);
        controlledTermSet.add(FRAME_SHIFT_INS);
        controlledTermSet.add(FRAME_SHIFT_DEL);
        controlledTermSet.add(THREE_PRIME_UTR);
        controlledTermSet.add(FIVE_PRIME_UTR);
        controlledTermSet.add(THREE_PRIME_FLANK);
        controlledTermSet.add(FIVE_PRIME_FLANK);
        controlledTermSet.add(IGR);
        controlledTermSet.add(INTRON);
        controlledTermSet.add(RNA);
        controlledTermSet.add(INDEL);
    }
}