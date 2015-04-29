def distance(strand_one, strand_two):

    # Ensure they are of equal lengths
    if len(strand_one) != len(strand_two):
        raise Exception('Unequal lengths')

    distance = 0

    for index in range(len(strand_one)):
        if strand_one[index] != strand_two[index]:
            distance += 1

    return distance
