def distance(a, b):
    '''Returns the hamming distance given two dna strands'''
    if len(a) != len(b):
        raise ValueError('Strands must be of equal length.')

    return sum(a[i] != b[i] for i in range(len(a)))
