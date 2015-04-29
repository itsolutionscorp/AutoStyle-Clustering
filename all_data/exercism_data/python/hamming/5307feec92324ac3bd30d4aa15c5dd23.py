def distance(a_strand, b_strand):
    """ Return Hamming distrance between two strands. """

    length = min(len(a_strand), len(b_strand))
    distance = 0

    for i in range(0, length):
        if a_strand[i] != b_strand[i]:
            distance = distance + 1

    return distance
