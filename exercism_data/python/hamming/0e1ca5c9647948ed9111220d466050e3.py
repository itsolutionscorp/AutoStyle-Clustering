def distance(strand1, strand2):
    count = 0
    for x in range(len(strand1)):
        if strand1[x] != strand2[x]:
            count += 1
    return count
