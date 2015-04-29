def to_rna(dna_sequence):
    complements = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna_sequence = ""
    for nucleotide in dna_sequence:
        rna_sequence += complements[nucleotide]
    return rna_sequence
