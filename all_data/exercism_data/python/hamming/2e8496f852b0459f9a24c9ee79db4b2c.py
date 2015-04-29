def distance(strand1, strand2):
    differences = 0

    for index in range(len(strand1)):
        if strand1[index] != strand2[index]:
            differences += 1

    return differences
