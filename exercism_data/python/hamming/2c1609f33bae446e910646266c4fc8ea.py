def distance(string1, string2):
    if len(string1) != len(string2):
        raise ValueError
    d = 0
    for i in range(len(string1)):
        if string1[i] != string2[i]:
            d += 1
    return d
