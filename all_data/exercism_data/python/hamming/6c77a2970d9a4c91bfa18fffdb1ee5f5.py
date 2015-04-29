def distance(a, b):
    p = zip(a,b)
    dist = 0
    for each in p:
        if each[0] is not each[1]:
            dist += 1

    return dist
