def to_rna(s):
    polymerase = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return ''.join(polymerase[b] for b in s)
