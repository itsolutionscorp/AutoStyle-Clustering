def to_rna(strand):
    conversion = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    rna = ''
    for nuc in strand:
        rna += conversion[nuc]
    return rna
