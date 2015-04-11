#Thanks to those who provided examples

from string import maketrans

def to_rna(dnaStr):
    return dnaStr.translate(maketrans("GCTA","CGAU"))
