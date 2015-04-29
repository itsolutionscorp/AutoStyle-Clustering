def distance(string1, string2):
    return sum(string1[i] != string2[i] for i in range(len(string1)))
