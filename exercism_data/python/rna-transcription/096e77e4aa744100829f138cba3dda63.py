def to_rna(dna):
    complements = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }
    return ''.join(complements[n] for n in dna)
