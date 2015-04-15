def distance(strand1, strand2):
    position = 0
    hamming_distance = 0

    for genome in strand1:
        if genome != strand2[position]:
            hamming_distance += 1

        position += 1

    return hamming_distance
