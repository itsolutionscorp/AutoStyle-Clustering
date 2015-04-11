import re

dna_to_rna_translation = str.maketrans("GCTA", "CGAU")

def to_rna(dna_strand):
    normalized = dna_strand.upper()

    if not re.match("^[GCTA\s]*$", normalized):
        raise ValueError(dna_strand + ": not a valid DNA strand")

    rna_strand = normalized.translate(dna_to_rna_translation)

    return rna_strand
