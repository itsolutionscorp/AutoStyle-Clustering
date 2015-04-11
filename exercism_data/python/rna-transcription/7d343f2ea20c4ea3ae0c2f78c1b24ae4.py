__author__ = 'greg'
DNA_TO_RNA = {'G':'C','C':'G','T':'A','A':'U'}

def to_rna(dna_string):
    """determines rna complement to dna string"""
    return ''.join([DNA_TO_RNA.get(n, ' ') for n in dna_string])
