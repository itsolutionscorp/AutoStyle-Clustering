def distance(first, second):
    """ Given two DNA strands, calculate and return the Hamming distance
    between them."""

    distance = 0
    pairs = zip(first, second)

    for p in pairs:
        if p[0] != p[1]:
            distance += 1

    return distance
