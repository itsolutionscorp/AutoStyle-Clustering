def to_rna(dna):
    dna_to_rna_map = {'G': 'C',
                      'C': 'G',
                      'T': 'A',
                      'A': 'U'}
    rna = ''
    for nucleo in dna:
        rna += dna_to_rna_map[nucleo]
    return rna
