DNA_TO_RNA_LOOKUP = {
    "G": "C",
    "C": "G",
    "T": "A",
    "A": "U"
}

def to_rna(dna):
    return ''.join(DNA_TO_RNA_LOOKUP[n.upper()] for n in dna)
