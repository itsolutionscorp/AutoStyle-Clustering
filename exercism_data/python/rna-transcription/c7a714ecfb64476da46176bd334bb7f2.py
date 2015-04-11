_lookup = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}


def to_rna(sequence):
    return ''.join([_lookup[sym] for sym in sequence])
