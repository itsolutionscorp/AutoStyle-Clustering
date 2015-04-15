def distance(str1, str2):
    result = 0
    for i, char in enumerate(str1):
        if str1[i] is not str2[i]:
            result += 1
    return result
