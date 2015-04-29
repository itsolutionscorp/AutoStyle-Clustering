def distance(str1, str2):
    result = 0
    for i,j in zip(str1,str2):
        if i != j:
            result += 1
    return result
