def to_rna(data):
    to_return = ""
    for letter in data:
        if letter == "G":
            to_return += "C"
        elif letter == "C":
            to_return += "G"
        elif letter == "T":
            to_return += "A"
        elif letter == "A":
            to_return += "U"
    return to_return
