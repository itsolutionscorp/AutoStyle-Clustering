def to_rna(dna):
    newdna = ""
    transform = {'T': 'A', 'A': 'U', 'C': 'G', 'G': 'C'}
    for x in dna:
        if x in transform:
            x = transform[x]
        newdna += x
    return newdna
