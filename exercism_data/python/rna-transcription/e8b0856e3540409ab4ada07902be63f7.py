TR = {
    'C': 'G',
    'G': 'C',
    'T': 'A',
    'A': 'U',
}


def to_rna(sequence):
    return ''.join(TR[el] for el in sequence)
