from string import maketrans

def to_rna(dna_string):
    """ Translates a string of DNA nucleotides to the RNA equivalents"""
    return dna_string.translate( maketrans("GCTA", "CGAU") )
