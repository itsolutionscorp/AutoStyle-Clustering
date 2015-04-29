__author__ = 'Dalton'


def hamming(dna1, dna2):
    count = 0
    if len(dna1) >= len(dna2):
        shorter = dna2
    else:
        shorter = dna1

    for i in range(len(shorter)):
        if dna1[i] != dna2[i]:
            count += 1

    return count + abs((len(dna1)-len(dna2)))
