__author__ = 'Dalton'

def to_rna(strand):
    
    return strand.replace('G', '%temp%').replace('C', 'G').replace('%temp%', 'C').replace('A', 'U').replace('T', 'A')
