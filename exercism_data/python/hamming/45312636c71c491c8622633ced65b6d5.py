def distance(str1, str2):
    if len(str1) != len(str2):
        raise ValueError('Sequences must be of equal length.')

    char_tuples = zip(str1, str2)
    return sum(1 for c1, c2 in char_tuples if c1 != c2)
