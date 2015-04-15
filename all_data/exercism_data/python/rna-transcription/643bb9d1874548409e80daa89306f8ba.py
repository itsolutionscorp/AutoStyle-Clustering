import re

def to_rna(inp):
    dna_rna_table = str.maketrans("GCTA","CGAU")
    inp = inp.translate(dna_rna_table)
    return inp;
