__author__ = 'greg'

DNA = 'GCTA'
RNA = 'CGAU'
DNA_TO_RNA = str.maketrans(DNA,RNA)

def to_rna(dna_string):
    """determines rna complement to dna string"""
    return dna_string.translate(DNA_TO_RNA)
