def distance(d1, d2):
    count = 0
    for (b1, b2) in zip(d1, d2):
        if b1 != b2:
            count += 1
    return count
