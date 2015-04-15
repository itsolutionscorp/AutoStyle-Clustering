dna_to_rna = {
    'A': 'U',
    'C': 'G',
    'G': 'C',
    'T': 'A'
}

def to_rna(sequence):
    return ''.join([dna_to_rna[nucleotide] for nucleotide in sequence])
