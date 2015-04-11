def to_rna(dna):
    rna = ''
    for letter in dna:
        if letter == "A":
            rna += "U"
        elif letter == "C":
            rna += "G"
        elif letter == "G":
            rna += "C"
        elif letter == "T":
            rna += "A"
        else:
            return "This isn't RNA."
    return rna
