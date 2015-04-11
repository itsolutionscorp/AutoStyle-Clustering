def hamming(str1,str2):
    dist = 0
    l1 = ''.join(str1)
    l2 = ''.join(str2)
    d1 = len(l1)
    d2 = len(l2)
    i = 0
    dist += max(d1,d2) - min(d1,d2)
    while i < min(d1,d2):
        if l1[i] == l2[i]:
            i += 1
        else:
            dist += 1
            i += 1
    return dist
