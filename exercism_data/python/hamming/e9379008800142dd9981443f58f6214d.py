''' This module computes the Hamming distance.
    Lesson: Take advantage of built-in functions.'''

def distance(dna1, dna2):
    '''Computes Hamming distance'''

    # hamming_dist = 0

    # if len(dna2) < len(dna1):
    #     dna1, dna2 = dna2, dna1

    # for indx, base in enumerate(dna1):
    #     if base != dna2[indx]:
    #         hamming_dist = hamming_dist + 1

    # return hamming_dist


    # Implementation using zip()
    # for i, j in zip(dna1, dna2):
    #     if i != j:
    #         hamming_dist = hamming_dist + 1
    # return hamming_dist

    # One-liner pythonic code with list comprehensions and sum()
    return sum(1 for i, j in zip(dna1, dna2) if i != j)
