__author__ = 'Mark'

dnarna_dict = {"G": "C", "C": "G", "T": "A", "A": "U"}

def to_rna(dna_string):
    rna_string = ""
    for i in dna_string:
        rna_string += dnarna_dict[i]
    return rna_string
