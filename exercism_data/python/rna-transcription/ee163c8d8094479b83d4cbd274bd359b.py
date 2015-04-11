code = {
    'A': 'U',
    'C': 'G',
    'G': 'C',
    'T': 'A',
}


def to_rna(x):
    r = []
    for i in x:
        r.append(code[i])
    return ''.join(r)
