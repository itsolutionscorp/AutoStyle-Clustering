def to_rna(r):
    new_list = []
    s = list(r)
    for i in s:
        if i == "G":
            new_list.append("C")
        elif i == "C":
            new_list.append("G")
        elif i == "T":
            new_list.append("A")
        elif i == "A":
            new_list.append("U")
    return ''.join(new_list)
