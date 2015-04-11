def distance(str1,str2):
    if len(str1) != len(str2):
        return None
    count = 0
    for key in list(zip(list(str1),list(str2))):
        if key[0] != key[1]:
            count += 1
    return count
