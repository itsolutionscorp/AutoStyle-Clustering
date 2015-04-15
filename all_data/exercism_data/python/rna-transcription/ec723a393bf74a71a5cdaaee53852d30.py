def to_rna(dna):

    dna = list(dna)

    for i in dna:
        if i == "G":
            i = "C"
        if i == "C":
            i = "G"
        if i == "T":
            i = "A"
        if i == "A":
            i = "U"
    dna = "".join(dna)
    print(dna)

to_rna("AAAA")
