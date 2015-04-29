dna_to_rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna_sequence):
    rna_sequence = ''
    for letter in dna_sequence:
        rna_sequence += dna_to_rna[letter]
        
    return rna_sequence
