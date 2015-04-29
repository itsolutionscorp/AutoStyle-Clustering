def to_rna(dna):
    rna = []
    for x in dna:
        if   x == "G": rna.append("C")
        elif x == "C": rna.append("G")
        elif x == "T": rna.append("A")
        elif x == "A": rna.append("U")
        else: raise ValueError("Invalid nucleotide in DNA strand: %s" % x)
    return "".join(rna)
