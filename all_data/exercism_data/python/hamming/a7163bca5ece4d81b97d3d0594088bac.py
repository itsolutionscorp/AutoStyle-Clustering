from itertools import izip_longest

def hamming(strand_a, strand_b):
    distance = 0
    for point_a, point_b in izip_longest(strand_a, strand_b):
        if point_a != point_b:
            distance += 1
    return distance
