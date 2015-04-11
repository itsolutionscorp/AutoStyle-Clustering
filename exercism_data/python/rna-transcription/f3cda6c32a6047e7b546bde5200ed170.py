def to_rna(dnaStr):
    res = ""
    for let in dnaStr:
        if let == "G":
            res += "C"
        elif let == "C":
            res += "G"
        elif let == "T":
            res += "A"
        else:
            res += "U"
    return res
