def distance(str1,str2):
    list1 = list(str1)
    list2 = list(str2)
    n = len(list1)
    num = 0
    while n > 0:
        n -= 1
        if list1[n] != list2[n]:
            num += 1
    return num
