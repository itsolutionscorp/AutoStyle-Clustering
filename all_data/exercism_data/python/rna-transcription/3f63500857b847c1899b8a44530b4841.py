def to_rna(dna):

    rna = ''
    for item in dna:

        if item == 'G':
            rna += 'C'
        elif item == 'C':
            rna += 'G'
        elif item == 'T':
            rna += 'A'
        elif item == 'A':
            rna += 'U'

    return rna
