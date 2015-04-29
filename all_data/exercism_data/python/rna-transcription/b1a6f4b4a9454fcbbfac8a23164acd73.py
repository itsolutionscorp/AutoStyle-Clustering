def to_rna(dna):
    rna = ""
    for nucleotides in dna:
        if (nucleotides == "G"):
            rna = rna+"C"
        if (nucleotides == "C"):
            rna = rna+"G"
        if (nucleotides == "T"):
            rna = rna+"A"
        if (nucleotides == "A"):
            rna = rna+"U"
    return rna
