DNA_MAP = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
}

def to_rna(dna):
    rna = ''
    for n in dna:
        rna += DNA_MAP[n]
    return rna
