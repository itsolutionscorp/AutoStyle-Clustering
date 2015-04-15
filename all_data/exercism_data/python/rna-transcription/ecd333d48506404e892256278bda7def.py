def to_rna(string):
    rna = ''
    map = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    for c in string:
        if c in map:
            rna += map[c]
        else:
            rna += c

    return rna
