def hamming(s, t):
    '''
    Find the hamming difference between 2 DNA strands
    '''
    return reduce(lambda n, (x, y): n + 1 if x != y else n, zip(s, t), abs(len(s) - len(t)))
