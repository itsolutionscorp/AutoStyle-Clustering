def to_rna(strand):
    a = ""
    for i in strand:
        if i == "G":
            a = a + "C"
        elif i == "C":
            a = a + "G"
        elif i == "T":
            a = a + "A"
        elif i == "A":
            a = a + "U"

    return a
