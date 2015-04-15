def distance(a, b):
    if len(a) != len(b):
        raise Error('Hamming distance is not defined for sequences of non-equal length')
    return len([ i for i,j in zip(a,b) if i != j])
