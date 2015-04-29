map = dict(zip('GCTA', 'CGAU'))

def to_rna(s):
    out = ''
    for c in s:
        out += map[c]
    return out
