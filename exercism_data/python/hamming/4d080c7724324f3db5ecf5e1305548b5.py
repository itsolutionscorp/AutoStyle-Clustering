def distance(strand1, strand2):
    if len(strand1) != len(strand2):
        raise ValueError('Strands must be of equal length')
    
    return sum(map(lambda x, y: x != y, strand1, strand2))
