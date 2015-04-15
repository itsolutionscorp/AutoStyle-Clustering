def to_rna(dna):
    trans = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    rna = [trans[x] for x in dna]
    return ''.join(rna)
