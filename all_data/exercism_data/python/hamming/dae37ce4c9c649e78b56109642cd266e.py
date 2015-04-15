def distance(r, s):
    dist = 0
    for ch1, ch2 in zip(r, s):
        if ch1 != ch2:
            dist += 1
    return dist
