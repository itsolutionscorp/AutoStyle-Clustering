
import string

def to_rna(what):
    rnamap = {'G':'C', 'C':'G','T':'A','A':'U'}
    out = []
    for char in what:
        out.append(rnamap[char])
    return string.join(out,'')



