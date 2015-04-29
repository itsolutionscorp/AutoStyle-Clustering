'''
Created on Oct 2, 2014

@author: bennettr
'''

def to_rna(DNAStrand):
    strand = list(DNAStrand)
    rnaStrand = []
    
    for nucleotide in DNAStrand:
        if nucleotide == 'G':
            rnaStrand.append('C')
        elif nucleotide == 'C':
            rnaStrand.append('G')
        elif nucleotide == 'T':
            rnaStrand.append('A')
        elif nucleotide == 'A':
            rnaStrand.append('U')
        else:
            print ('%s is an invalid DNA nucleotide. Please enter a valid DNA sequence') % nucleotide
            return    
   
    return ''.join(rnaStrand)
