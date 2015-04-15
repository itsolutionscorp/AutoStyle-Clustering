def distance(strand1, strand2):
    count = 0
    shortest = min(len(strand1), len(strand2))

    for i in range(shortest):
        if strand1[i] != strand2[i]:
            count += 1

    return count
