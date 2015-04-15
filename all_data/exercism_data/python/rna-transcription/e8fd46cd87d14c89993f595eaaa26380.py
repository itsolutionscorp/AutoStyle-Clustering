replacements = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}


def to_rna(string):
    return ''.join([replacements[l] for l in string])
