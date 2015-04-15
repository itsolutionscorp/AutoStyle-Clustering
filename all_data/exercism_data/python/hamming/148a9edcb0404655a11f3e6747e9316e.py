# exercism problem 5 compute hamming distance

    
def distance(dna_1, dna_2):

    if len(dna_1) != len(dna_2):
        raise ValueError('Strings must be of equal length.')

    hamming_distance = sum(1 for c1, c2 in zip(dna_1, dna_2) if c1 != c2)

    return hamming_distance
