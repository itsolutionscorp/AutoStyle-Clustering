def to_rna(dna):
    dna = list(dna)
    for i in range(0,len(dna)):
        if dna[i] == "G":
            dna[i] = "C"
        elif dna[i] == "C":
            dna[i] = "G"
        elif dna[i] == "T":
            dna[i] = "A"
        elif dna[i] == "A":
            dna[i] = "U"
    rna = "".join(dna)
    return rna
    
    
