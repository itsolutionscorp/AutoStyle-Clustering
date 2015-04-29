def distance(str1, str2):
    return sum(1 for np in zip(str1, str2) if np[0]!=np[1])
