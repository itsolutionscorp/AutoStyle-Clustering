def to_rna(dna):
    comps = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    
    return ''.join((comps[c] for c in dna))
