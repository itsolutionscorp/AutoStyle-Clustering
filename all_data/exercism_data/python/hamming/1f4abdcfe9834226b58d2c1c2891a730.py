def distance(s1, s2):
    dist = 0

    if len(s1) == len(s2):
        for (ind, ch) in enumerate(s1):
            if ch != s2[ind]:
                dist += 1
    else:
        raise IndexError("Strings are not of equal length")

    return dist
