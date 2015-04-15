

def distance(strand1, strand2):
    distance_count = 0
    for n in range(len(strand1)):
        if strand1[n] != strand2[n]:
            distance_count += 1

    return distance_count
