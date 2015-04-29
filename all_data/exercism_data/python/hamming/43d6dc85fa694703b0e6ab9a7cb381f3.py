def hamming(str1,str2):
    dist = 0
    d1 = len(str1)
    d2 = len(str2)
    i = 0
    dist += abs(d1 - d2)
    while i < min(d1,d2):
        if str1[i] == str2[i]:
            i += 1
        else:
            dist += 1
            i += 1
    return dist
