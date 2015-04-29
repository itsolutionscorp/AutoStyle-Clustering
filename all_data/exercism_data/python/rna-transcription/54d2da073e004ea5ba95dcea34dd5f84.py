translation = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}


def to_rna(dna):
    return ''.join(translation[a] for a in dna)
