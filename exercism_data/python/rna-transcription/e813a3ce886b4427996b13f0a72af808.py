def to_rna(x):
    s = ''
    for y in x:
        if y == 'G':
            s += 'C'
        elif y == 'C':
            s += 'G'
        elif y == 'T':
            s += 'A'
        else: s += 'U'
    return s
