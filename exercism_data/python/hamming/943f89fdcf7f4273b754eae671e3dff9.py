def hamming(strand, mutation):
    distance = int()
    for i in range(min(len(x) for x in (strand, mutation))):
        if strand[i] != mutation[i]:
            distance += 1
    distance += abs(len(strand) - len(mutation))
    return distance
