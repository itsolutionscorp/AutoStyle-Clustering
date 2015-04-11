def distance(str1, str2):
    mind = min(len(str1),len(str2))
    hd = 0
    for i in xrange(mind):
        if str1[i] != str2[i]:
            hd +=1
    return hd
