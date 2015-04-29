#
#
#

from string import maketrans
def to_rna(dna):
    trantab = maketrans('GCTA','CGAU')
    return dna.translate(trantab)

