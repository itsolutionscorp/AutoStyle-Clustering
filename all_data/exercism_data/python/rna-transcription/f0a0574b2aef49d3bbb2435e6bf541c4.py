def to_rna(inpt):
    out=""
    for letter in inpt:
        if letter == "C":
            out += "G"
        if letter == "G":
            out += "C"
        if letter == "T":
            out += "A"
        if letter == "A":
            out += "U"
    return out
