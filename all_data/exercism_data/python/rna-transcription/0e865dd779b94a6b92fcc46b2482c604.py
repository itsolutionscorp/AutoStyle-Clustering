'''
Created on Sep 24, 2014

@author: dhawkins
'''

def to_rna(dna):
    dna = dna.upper()
    trans_dict = {'G': 'C',
                  'C': 'G',
                  'T': 'A',
                  'A': 'U'}
    
    return ''.join([trans_dict[c] for c in dna])
