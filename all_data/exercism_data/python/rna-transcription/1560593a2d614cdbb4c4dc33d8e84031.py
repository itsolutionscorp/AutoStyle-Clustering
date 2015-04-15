def to_rna(dna):
    upper_dna = dna.upper()
    rna = upper_dna
    rna = rna.replace("G", "P")
    rna = rna.replace("C", "G")
    rna = rna.replace("P", "C")
    rna = rna.replace("A", "U")
    rna = rna.replace("T", "A")
    
    return rna
