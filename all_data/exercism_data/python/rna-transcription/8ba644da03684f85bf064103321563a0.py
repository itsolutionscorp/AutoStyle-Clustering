def to_rna(dna):
    rna = ""
    for character in dna:
        if character == "C":
            rna += "G"
        elif character == "G":
            rna += "C"
        elif character == "A":
            rna += "U"
        elif character == "T":
            rna += "A"
    return rna
