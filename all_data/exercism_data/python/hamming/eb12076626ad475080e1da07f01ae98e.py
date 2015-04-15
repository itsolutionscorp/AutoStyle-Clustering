#Calculates the hamming distance between two strands of DNA

def distance(dna1, dna2):
    if (len(dna1) != len(dna2)):
        return "Error: The length of the two dna strings does not match!"

    hamming_distance = 0

    for i in range(0, len(dna1)):
        if (dna1[i] != dna2[i]):
            hamming_distance += 1

    return hamming_distance
