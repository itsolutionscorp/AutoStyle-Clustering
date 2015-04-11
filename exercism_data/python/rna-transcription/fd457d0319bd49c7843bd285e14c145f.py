



def to_rna(dna_seq):
    dna = 'GCTA'
    rna = 'CGAU'
    z = {}

    for ch1, ch2 in zip(dna, rna):
        z[ch1] = ch2

    rna_seq = ''
    for s in dna_seq:
        rna_seq += z[s]

    return rna_seq
