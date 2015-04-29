def to_rna(text):
    pivot = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }
    return ''.join([pivot[l] for l in text])
