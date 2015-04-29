import re

dna_to_rna_translation = str.maketrans("GCTA", "CGAU")

def to_rna(dna):
    normalized = dna.upper()

    if not re.match("^[GCTA\s]*$", normalized):
        raise ValueError(dna + ": not a valid DNA sequence")

    rna = normalized.translate(dna_to_rna_translation)

    return rna
