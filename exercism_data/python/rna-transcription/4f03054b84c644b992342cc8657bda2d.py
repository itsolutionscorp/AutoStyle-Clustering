def to_rna(dna):
    rna = ''
    for x in dna:
        if x == 'G':
            rna = rna + 'C'
        if x == 'C':
            rna = rna + 'G'
        elif x == 'T':
            rna = rna + 'A'
        elif x == 'A':
            rna = rna + 'U'
    return rna
