def to_rna(sec):
    res = ''
    for nuc in sec:
        if nuc == 'G':
            res += 'C'
        elif nuc == 'C':
            res += 'G'
        elif nuc == 'T':
            res += 'A'
        elif nuc == 'A':
            res += 'U'

    return res
