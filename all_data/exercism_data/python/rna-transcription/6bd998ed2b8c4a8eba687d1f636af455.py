
def to_rna(what):
    rnamap = {'G':'C', 'C':'G','T':'A','A':'U'}
    out = ''
    for char in what:
        out += rnamap[char]
    return out



