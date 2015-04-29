trans = {
    'A': 'U',
    'C': 'G',
    'G': 'C',
    'T': 'A',
}

def to_rna(what):
    return ''.join(trans[a] for a in what)
