def to_rna(dna):
    rna = ''
    for base in dna:
        if base == "A":
            rna += "U"
        elif base == "T":
            rna += "A"
        elif base == "G":
            rna += "C"
        elif base == "C":
            rna += "G"
    return rna
