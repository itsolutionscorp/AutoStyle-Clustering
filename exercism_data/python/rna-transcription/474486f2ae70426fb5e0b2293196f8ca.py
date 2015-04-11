def to_rna(sequence):
    rna = ''
    for strand in sequence:
        rna += {
            'G': 'C',
            'C': 'G',
            'T': 'A',
            'A': 'U'
        }[strand]
    return rna
