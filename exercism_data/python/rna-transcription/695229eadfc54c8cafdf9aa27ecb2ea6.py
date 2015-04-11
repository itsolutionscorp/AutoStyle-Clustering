def to_rna(dna):
    rna = ''
    for nuc in dna:
        if nuc is 'G': rna += 'C'
        elif nuc is 'C': rna += 'G'
        elif nuc is 'T': rna += 'A'
        elif nuc is 'A': rna += 'U'

    return rna
