def distance(str1, str2):
    '''Returns the hamming distance given two dna strands'''
    dist = 0
    for i, char in enumerate(str1):
        if char != str2[i]: dist += 1

    return dist
