def to_rna(dna):
    table = {'A' : 'U', 'T' : 'A', 'C' : 'G', 'G' : 'C'}
    rna = ''
    for i in dna:
        rna += table[i]
    return rna
