def to_rna(dna):
    return dna.lower().replace("a", "U").replace("t", "A").replace("c", "G").replace("g", "C")
