mapping = dict(zip('GCTA', 'CGAU'))

def to_rna(s):
    out = ''
    for c in s:
        out += mapping[c]
    return out
