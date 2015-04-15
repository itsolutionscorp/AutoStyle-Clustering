def distance(string1, string2):
    return sum([1 if pair[0]!=pair[1] else 0 for pair in zip(string1, string2)])
