def distance(DNA1, DNA2):
    dist = 0
    for c, c2 in zip(DNA1, DNA2):
        if c != c2:
            dist += 1
    return dist
