def to_rna(dna):
    d = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    return "".join([d[c] for c in dna])
