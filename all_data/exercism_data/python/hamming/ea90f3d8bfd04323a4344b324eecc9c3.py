def distance(strand1, strand2):
    z = zip(strand1, strand2)
    count = 0
    for k, v in z:
        if k != v:
            count += 1
    return count
