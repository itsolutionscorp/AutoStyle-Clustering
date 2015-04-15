def hamming(strand1, strand2):
    distance = [0]
    def check(nuc1, nuc2):
        if nuc1 != nuc2:
            distance[0] += 1

    map(check, strand1, strand2)
    return distance[0]
