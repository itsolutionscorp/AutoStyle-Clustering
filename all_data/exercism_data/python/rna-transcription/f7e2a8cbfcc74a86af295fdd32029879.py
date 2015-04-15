COMPLEMENTS = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}

def to_rna(dna):
    return ''.join([COMPLEMENTS[i] for i in dna])
