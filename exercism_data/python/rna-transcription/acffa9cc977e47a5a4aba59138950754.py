complements = {
    "G": "C",
    "C": "G",
    "T": "A",
    "A": "U"
}

def to_rna(dna):
    rna = ""
    for nucleotide in dna:
        rna += complements.get(nucleotide, "")
    return rna
