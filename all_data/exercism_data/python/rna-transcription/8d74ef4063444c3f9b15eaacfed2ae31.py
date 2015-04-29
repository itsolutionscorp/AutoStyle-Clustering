def to_rna(dna):
    rna = ""
    for n in dna:
        if n == 'G':
            rna += 'C'
        elif n == 'C':
            rna += 'G'
        elif n == 'T':
            rna += 'A'
        elif n == 'A':
            rna += 'U'
        else:
            # I couldn't resist
            rna += 'TMNT'
    return rna
