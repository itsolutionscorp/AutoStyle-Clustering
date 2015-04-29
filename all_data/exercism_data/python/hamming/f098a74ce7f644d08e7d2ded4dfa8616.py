def distance(strand1, strand2):
    value = 0
    if len(strand1) != len(strand2): return value
    
    for x, y in zip(strand1,strand2):
        value+= (x != y)
    return value
    
