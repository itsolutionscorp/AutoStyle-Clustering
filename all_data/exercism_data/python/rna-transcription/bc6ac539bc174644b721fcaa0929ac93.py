'''
Created on Oct 10, 2014

@author: jbarni00
'''

def to_rna(sequence):
    rna = []
    sequencelist = list(sequence)
    
    for item in sequencelist:
        if (item == 'G'):
            rna.append('C')
        elif (item == 'C'):
            rna.append('G')
        elif (item == 'T'):
            rna.append('A')
        elif (item == 'A'):
            rna.append('U')
    return ''.join(rna)
