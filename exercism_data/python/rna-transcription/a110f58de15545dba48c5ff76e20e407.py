'''
Created on Feb 25, 2015

@author: Thor
'''
def to_rna(self, DNAstrand):
    RNA = ""
    for nucleotide in DNAstrand:
        if nucleotide == 'C':
            RNA += 'G'
        elif nucleotide == 'G':
            RNA += 'C'
        elif nucleotide == 'A':
            RNA += 'T'
        elif nucleotide == 'U':
            RNA += 'A'
    return RNA
