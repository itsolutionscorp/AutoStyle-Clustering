# File: dna.py
# Author: Theo Love
# Date: 201-09-23
# Description: Help turn DNA in to RNA

def to_rna(dna):
    # turn the dna in to an uppercase string
    dna = str(dna).upper()
    rna = ''
    
    #convert each character
    for c in dna:
        if c == 'G':
            rna = rna + 'C'
        elif c == 'C':
            rna = rna + 'G'        
        elif c == 'T':
            rna = rna + 'A'
        elif c == 'A':
            rna = rna + 'U'
        else:
            pass
            
    return rna
