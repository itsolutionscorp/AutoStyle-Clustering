def __to_rna_single_char(dna_char):
    return {
        "G": "C",
        "C": "G",
        "T": "A",
        "A": "U"
    }[dna_char]


def to_rna(dna):
    rna = ""
    for char in dna:
        rna = rna + __to_rna_single_char(char)

    return rna
