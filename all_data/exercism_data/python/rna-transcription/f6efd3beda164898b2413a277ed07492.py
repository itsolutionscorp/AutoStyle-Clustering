def to_rna(dna_strand):
    dna_to_rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    rna_strand = ''.join(dna_to_rna[base] for base in dna_strand)

    return rna_strand
