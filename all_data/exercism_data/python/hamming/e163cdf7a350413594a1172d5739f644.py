def distance(sequence1, sequence2):

    distance = 0

    for letter in range(len(sequence1)):

        if sequence1[letter] != sequence2[letter]:
            distance += 1

    return distance
