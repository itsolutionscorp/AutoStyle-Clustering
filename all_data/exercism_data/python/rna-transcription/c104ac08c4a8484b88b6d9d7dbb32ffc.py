def to_rna(rsa):
    rna = ''
    for char in rsa:
        if char == 'A':
            rna += 'U'
        if char == 'C':
            rna += 'G'
        if char == 'G':
            rna += 'C'
        if char == 'T':
            rna += 'A'
    return rna
