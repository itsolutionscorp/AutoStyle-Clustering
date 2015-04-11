def to_rna(dna):
    transform = {'T': 'A', 'A': 'U', 'C': 'G', 'G': 'C'}
    l = [transform[x] for x in dna]
    newdna = "".join(l)
    return newdna
