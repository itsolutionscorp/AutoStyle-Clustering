import string

def to_rna(dna):
    intab = 'GCTA'
    outtab = 'CGAU'
    trantab = string.maketrans(intab, outtab)
    return dna.translate(trantab)
