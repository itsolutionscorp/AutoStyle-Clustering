def to_rna(strand):
    table = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    return ''.join([table[i] for i in strand])
