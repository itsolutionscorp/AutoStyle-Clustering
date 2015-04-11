def to_rna(dna):
    rna = list(dna)
    for i in range(0,len(rna)):
        if rna[i] == "G":
            rna[i] = "C"
        elif rna[i] == "C":
            rna[i] = "G"
        elif rna[i] == "T":
            rna[i] = "A"
        elif rna[i] == "A":
            rna[i] = "U"
    rna = "".join(rna)
    return rna
    
    
