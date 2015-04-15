def distance(start, end):
    index = 0
    hamming_distance = 0

    for letter in start:
        if letter is not end[index]:
            hamming_distance += 1

        index += 1

    return hamming_distance
