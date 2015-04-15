def distance(str1, str2):
    '''Returns the hamming distance given two dna strands'''
    if len(str1) != len(str2):
        raise ValueError('Strands must be of equal length.')

    dist = 0
    for a, b in zip(str1, str2):
        if a != b: dist += 1

    return dist
