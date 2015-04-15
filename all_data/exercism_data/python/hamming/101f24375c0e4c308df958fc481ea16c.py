def distance(str1,str2):
    count = 0
    for i in range(0,len(str1)):
        if str1[i] != str2[i]:
            count += 1

    return count
