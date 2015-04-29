def distance(str1,str2):
    d = 0
    list1, list2 = list(str1),list(str2)
    for i in range(0, len(str1)):
        if list1[i] != list2[i]: 
            d += 1
    return d
