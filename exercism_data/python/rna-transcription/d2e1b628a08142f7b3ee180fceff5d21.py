



def to_rna(dna_seq):
    dna = 'GCTA'
    rna = 'CGAU'
    z = {}
    for x in range(0,4):
        z[dna[x]] = rna[x]

    rna_seq = ''
    for s in dna_seq:
        rna_seq += z[s]

    return rna_seq
