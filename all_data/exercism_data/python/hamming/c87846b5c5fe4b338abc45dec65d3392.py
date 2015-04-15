#Calculates the hamming distance between two strands of DNA

def distance(dna1, dna2):
    if (len(dna1) != len(dna2)):
        return "Error: The length of the two dna strings does not match!"

    hamming_distance = 0

    for nucleotides in zip(dna1, dna2):
        if (nucleotides[0] != nucleotides[1]):
            hamming_distance += 1

    return hamming_distance
