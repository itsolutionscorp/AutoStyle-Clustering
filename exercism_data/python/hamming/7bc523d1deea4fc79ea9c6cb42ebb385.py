def distance(first, second):
    if len(first) != len(second):
        return None # not sure if this is pythonic

    dist = 0
    for i, base in enumerate(first):
        if first[i] != second[i]:
            dist += 1

    return dist
