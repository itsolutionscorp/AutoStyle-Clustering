def to_rna(dna):
    rna = ""
    for x in dna:
        if x == "G":
            rna += "C"
        if x == "C":
            rna += "G"
        if x == "T":
            rna += "A"
        if x == "A":
            rna += "U"
    return rna
