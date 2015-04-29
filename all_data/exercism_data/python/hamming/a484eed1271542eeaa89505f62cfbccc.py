def distance(first_strand, second_strand):
    hamming_distance = 0
    for x, y in zip(first_strand, second_strand):
        if x != y:
            hamming_distance += 1
    return hamming_distance
