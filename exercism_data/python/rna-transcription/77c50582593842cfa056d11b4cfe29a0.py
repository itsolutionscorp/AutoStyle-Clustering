"""A DNA to RNA simulator. Given a string of valid DNA base-pairs
(G,C,T,A), the function returns a string representing the corresponsing
RNA base-pairs (C,G,A,U)"""

import sys

def to_rna(dna):
    """String of DNA nucleoide => Strong of RNA nucleoide"""
    if sys.version_info[0] == 2:
        import string
        translation_function = string.maketrans
    elif sys.version_info[0] == 3:
        translation_function = str.maketrans

    return(dna.translate(translation_function("GCTA", "CGAU")))
