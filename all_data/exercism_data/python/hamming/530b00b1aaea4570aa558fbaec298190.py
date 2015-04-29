from itertools import izip


def distance(a, b):
    assert len(a) == len(b)

    hamming_distance = 0

    for position in izip(a, b):
        if position[0] != position[1]:
            hamming_distance += 1

    return hamming_distance
