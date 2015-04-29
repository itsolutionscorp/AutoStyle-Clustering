def to_rna(s):
    rna = []
    for c in list(s):
        rna.append({
            'G': 'C',
            'C': 'G',
            'A': 'U',
            'T': 'A',
        }[c])
    return ''.join(map(str, rna))
