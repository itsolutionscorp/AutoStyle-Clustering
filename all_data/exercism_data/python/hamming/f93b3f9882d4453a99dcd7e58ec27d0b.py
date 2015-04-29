def distance(str1,str2):
    dist = 0
    for n,b1 in enumerate(str1):
        if b1 != str2[n]:
            dist += 1
    return dist
