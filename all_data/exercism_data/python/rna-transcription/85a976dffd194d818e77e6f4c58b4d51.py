def to_rna(dna):
    res = ''
    for char in dna:
        if char == 'G':
            res = res + 'C'
        elif char == 'C':
            res = res + 'G'
        elif char == 'T':
            res = res + 'A'
        elif char == 'A':
            res = res + 'U'
    return res
