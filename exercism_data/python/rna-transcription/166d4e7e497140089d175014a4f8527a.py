import re

def to_rna(dna)
    dna_to_rna = {
        "A": "U",
        "T": "A",
        "C": "G",
        "G": "C",
    }
    return re.sub("[A-Z]", lambda x: dna_to_rna[x.group(0)], dna)
