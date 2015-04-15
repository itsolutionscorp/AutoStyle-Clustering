import re
import string

def to_rna(dna):
    if re.search(r"[^GCTA]", dna) is not None:
        raise "Invalid DNA strand."
    trans = string.maketrans('GCTA', 'CGAU')
    return string.translate(dna, trans)
