def to_rna(str):
    dna_list = list(str)
    rna_str = ""
    for dna in dna_list:
        if (dna == "C"):
            rna_str += "G"
        elif (dna == "G"):
            rna_str += "C"
        elif (dna == "A"):
            rna_str += "U"
        elif (dna == "T"):
            rna_str += "A"
    return rna_str
