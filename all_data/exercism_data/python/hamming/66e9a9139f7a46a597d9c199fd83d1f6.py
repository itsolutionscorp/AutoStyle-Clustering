def hamming(s1,s2):

    return reduce(lambda x,y: x + (y[0]!=y[1]), zip(s1,s2), 0) + abs(len(s1)-len(s2))
