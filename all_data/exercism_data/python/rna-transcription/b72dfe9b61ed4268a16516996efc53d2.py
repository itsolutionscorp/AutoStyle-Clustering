from string import maketrans
import re

def to_rna(string):
    dna = re.sub(r'[^GCTA]', '', string.upper())
    return dna.translate(maketrans("GCTA", "CGAU"))
