__author__ = 'Mark'

dnarna_dict = {"G": "C", "C": "G", "T": "A", "A": "U"}

def to_rna(dna_string):
    rna_string = ""
    for i in dna_string:
        rna = dnarna_dict[i]
        rna_string += rna
    return rna_string
