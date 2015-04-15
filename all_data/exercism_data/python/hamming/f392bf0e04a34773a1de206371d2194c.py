'''hamming exercise'''

def hamming(dna1, dna2):
    '''return the hamming distance between the two chains of nucleotides'''

    # start distance at difference between the two chains
    distance = abs(len(dna1) - len(dna2))
    # count the remaining differing nucleotides
    for n1, n2 in zip(dna1, dna2):
        if n1 != n2:
            distance += 1

    return distance
