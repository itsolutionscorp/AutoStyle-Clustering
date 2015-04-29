def distance(first_sequence, second_sequence):
    if len(first_sequence) != len(second_sequence):
        raise ValueError('different length sequences')

    hamming_distance = 0
    for index in xrange(0, len(first_sequence)):
        if first_sequence[index] != second_sequence[index]:
            hamming_distance = hamming_distance + 1
    return hamming_distance
