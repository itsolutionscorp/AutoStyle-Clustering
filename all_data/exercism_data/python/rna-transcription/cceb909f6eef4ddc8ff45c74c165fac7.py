#
# to_rna function
#

def to_rna(dna):
    intab = 'GCTA'
    outtab = 'CGAU'
    transtable = dna.maketrans(intab,outtab)
    rna = dna.translate(transtable)
    return rna
