from string import maketrans
from string import translate

intab = 'GCTA'
outtab = 'CGAU'
gene_map = maketrans(intab,outtab)

def to_rna(strand):
    return strand.translate(gene_map)
