def distance(str1, str2):
    ham = 0
    for char in range(0, min(len(str1), len(str2))):
        if str1[char] != str2[char]:
            ham += 1
    return ham + abs(len(str1)-len(str2))
