__author__ = 'Micha'


def distance(dna1, dna2):
    print(dna1, " ", dna2)
    if len(dna1) == len(dna2):
        dist = 0
        for x in range(len(dna1)):
            if dna1[x] != dna2[x]:
                dist += 1
    return dist


