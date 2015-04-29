def distance(str1, str2):
    ham = 0
    if len(str1) == len(str2):
        for char in range(0, len(str1)):
            if str1[char] != str2[char]:
                ham += 1
    else:
        return "Strings not equal length"
    return ham
