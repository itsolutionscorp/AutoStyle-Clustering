dna2rna = {
    'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U',
}

def to_rna(dna):
    return ''.join(dna2rna[c] for c in dna)
