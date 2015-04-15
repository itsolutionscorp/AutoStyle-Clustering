def to_rna(dna):
    return ''.join(transform(c) for c in dna)

def transform(dna):
    return {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }[dna]
