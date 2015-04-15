RNA_COMPLEMENT = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}

def to_rna(dna_strands):
    rna_strand = ""
    for dna_strand in dna_strands:
        rna_strand += RNA_COMPLEMENT[dna_strand]

    return rna_strand
