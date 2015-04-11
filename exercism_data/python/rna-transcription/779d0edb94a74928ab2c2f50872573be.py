dna_to_rna = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
    'U': 'A',
}

def to_rna(sequence):
    rna_sequence = ""

    for nucleotide in sequence:
        rna_sequence += dna_to_rna[nucleotide]

    return rna_sequence
