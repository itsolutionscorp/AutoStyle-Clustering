from string import maketrans
def to_rna(seq):
    dna = 'ATGC'
    rna  = 'UACG'
    trans = maketrans(dna,rna)
    return seq.translate(trans)
