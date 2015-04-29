def to_rna(dna):
    rna = ""

    for letter in dna:
        if letter == "G":
            rna += "C"
        elif letter == "C":
            rna += "G"
        elif letter == "T":
            rna += "A"
        elif letter == "A":
            rna += "U"

    return rna
