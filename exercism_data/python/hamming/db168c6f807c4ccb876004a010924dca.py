def distance(str1, str2):
    return [True for (s1, s2) in zip(str1, str2) if s1 != s2].count(True)
