def to_rna(dna):
    rna = ''
    for nuc in dna:
        if nuc == 'G':
            rna += 'C'
        elif nuc == 'C':
            rna += 'G'
        elif nuc == 'T':
            rna += 'A'
        elif nuc == 'A':
            rna += 'U'
    return rna
