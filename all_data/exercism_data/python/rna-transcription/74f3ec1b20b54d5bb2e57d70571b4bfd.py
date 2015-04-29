def to_rna(dna):
    rna = ''
    for n in dna:
        if n == 'G':
            rna += 'C'
        if n == 'C':
            rna += 'G'
        if n == 'T':
            rna += 'A'
        if n == 'A':
            rna += 'U'
    return rna
