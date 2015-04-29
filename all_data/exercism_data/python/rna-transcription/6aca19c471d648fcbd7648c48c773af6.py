def to_rna(input):
    return "".join(map(lambda x: to_rna2(x), input))

def to_rna2(input):
    if input == "G":
        return "C"
    if input == "C":
        return "G"
    if input == "T":
        return "A"
    if input == "A":
        return "U"
