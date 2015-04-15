def distance(strand1, strand2):
    count = 0
    for x, y in zip(strand1, strand2):
        if x != y:
            count += 1
    return count
