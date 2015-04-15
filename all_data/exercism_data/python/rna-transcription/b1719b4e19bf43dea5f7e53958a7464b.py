def to_rna(dna):
    trans = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    return ''.join([trans[x] for x in dna])
