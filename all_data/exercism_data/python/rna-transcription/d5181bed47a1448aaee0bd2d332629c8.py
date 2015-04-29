import re

def to_rna(dna):
    dna_to_rna_map = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }

    return re.sub(r"[GCTA]", lambda x: dna_to_rna_map[x.group(0)], dna)
