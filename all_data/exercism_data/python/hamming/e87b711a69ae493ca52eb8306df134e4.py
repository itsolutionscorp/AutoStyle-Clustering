def distance(a,b):
    if len(a) != len(b):
        raise Exception('Sequences are not equal length')
    return sum(x != y for x,y in zip(a,b))
