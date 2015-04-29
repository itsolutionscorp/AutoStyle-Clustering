def dnaToRna(dna):
    replacement = {
            "G": "C",
            "C": "G",
            "T": "A",
            "A": "U",
            }

    return "".join(replacement.get(c, c) for c in dna)
