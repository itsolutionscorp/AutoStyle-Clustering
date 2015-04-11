def to_rna(dna_strand):
    dna_to_rna_mapping = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return ''.join(dna_to_rna_mapping[dna] for dna in dna_strand)
