def to_rna(dna):
    rna = ''
    for ch in dna:
        if ch == 'C':
            rna += 'G'
        if ch == 'G':
            rna += 'C'
        if ch == 'A':
            rna += 'U'
        if ch == 'T':
            rna += 'A'
    return(rna)
