def distance(str1, str2):
    return sum(char1 != char2 for char1, char2 in zip(str1, str2))
