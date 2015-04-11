def hamming(str1, str2):
    hd = 0
    hd += abs(len(str1) - len(str2))
    for a, b in zip(str1, str2):
        if a != b:
            hd += 1
    return hd
