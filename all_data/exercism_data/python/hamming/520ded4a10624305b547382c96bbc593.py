def hamming(s1,s2):
    return sum(1 for x,y in map(None,s1,s2) if x!=y)
