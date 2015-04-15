def to_rna(string):
    rna = ''
    map = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    for c in string:
        rna += map[c]

    return rna
