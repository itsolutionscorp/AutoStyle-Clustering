complements = {
        "G": "C",
        "C": "G",
        "T": "A",
        "A": "U"
}

def to_rna(dna):
    return "".join([complements.get(char, "") for char in dna])
