def distance(str1, str2):
    char_tuples = zip(str1, str2)
    return sum(1 for c1, c2 in char_tuples if c1 != c2)
