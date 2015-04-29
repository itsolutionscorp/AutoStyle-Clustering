def to_rna(dna_strand):
    rna_strand = ''
    
    dna_to_rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    for base in dna_strand:
        rna_strand += dna_to_rna[base]

    return rna_strand
