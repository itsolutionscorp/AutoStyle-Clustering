## dna.py

__author__="cdances"

from string import maketrans

rna_lookup=maketrans("GCTA","CGAU")

def to_rna(dna_chain):
    
    return dna_chain.translate(rna_lookup)
        
