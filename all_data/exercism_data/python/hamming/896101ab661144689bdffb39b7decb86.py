# Hamming test


def distance(str1, str2):
    if len(str1) != len(str2):
        return Exception
    counter = 0
    for i in range(len(str1)):
        if str1[i] != str2[i]:
            counter += 1
    return counter
