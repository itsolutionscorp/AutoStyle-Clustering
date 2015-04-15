complement = { 'G': 'C',
               'C': 'G',
               'T': 'A',
               'A': 'U',}

def to_rna(sequence):
    rna = ''
    for base in sequence:
        rna += complement[base]
    return rna
