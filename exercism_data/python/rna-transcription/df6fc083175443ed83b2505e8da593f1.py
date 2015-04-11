def to_rna(dna_strand):

    # RNA Transcription
    DNA_TO_RNA_MAP = {
        'G' : 'C',
        'C' : 'G',
        'T' : 'A',
        'A' : 'U'
    }

    rna_complement = [DNA_TO_RNA_MAP.get(dna) for dna in dna_strand]
    return ''.join(rna_complement)
