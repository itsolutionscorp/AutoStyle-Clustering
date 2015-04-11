def to_rna(dna):
    compl = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }
    return ''.join([compl[nuc] for nuc in dna])
