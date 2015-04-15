def is_dna(sequence):
    return set(sequence) == set(("A", "C", "G", "T"))


dna_to_rna = str.maketrans({"G": "C",
                            "C": "G",
                            "T": "A",
                            "A": "U"})



def to_rna(dna : is_dna) -> str:
    return dna.translate(dna_to_rna)
