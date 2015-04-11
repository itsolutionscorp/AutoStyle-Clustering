def distance(dna1, dna2):
    if len(dna2) != len(dna1):
        return "Not the same length"

    hammingCounter = 0

    for i in range(len(dna1)):
        if dna1[i] != dna2[i]:
            hammingCounter += 1

    return hammingCounter
