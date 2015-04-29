'''hamming exercise'''

def hamming(dna1, dna2):
    '''return the hamming distance between the two chains of nucleotides'''

    # normalize the strand length
    strand_diff = len(dna1) - len(dna2)
    if strand_diff < 0:
        dna1 += '-' * abs(strand_diff)
    elif strand_diff > 0:
        dna2 += '-' * strand_diff

    # count the differing nucleotides
    distance = 0
    for n1, n2 in zip(dna1, dna2):
        if n1 != n2:
            distance += 1

    return distance
