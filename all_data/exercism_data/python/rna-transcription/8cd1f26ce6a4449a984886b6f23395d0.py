encoder = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}

def to_rna(seq):
    return ''.join([encoder[s] for s in seq])
