def to_rna(dna):
    o = ''
    for b in dna:
        if b == 'A':
            o += 'U'
        elif b == 'T':
            o += 'A'
        elif b == 'C':
            o += 'G'
        elif b == 'G':
            o += 'C'
    return o
