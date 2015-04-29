def to_rna(secuence):

    dna_complements = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    dna_secuence = list(secuence)

    rna = ''
    for nucleo in dna_secuence:
        rna += dna_complements[nucleo]

    return rna
