def to_rna(code):
    rna = ''
    for dna in code:
        rna += mapper(dna)
    return rna


def mapper(dna):
    map = {'G': 'C',
           'C': 'G',
           'T': 'A',
           'A': 'U'}
    return map[dna.upper()]

# EOF
