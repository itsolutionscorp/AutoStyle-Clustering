def distance(strand1, strand2):
    return len([ 1 for s1, s2 in zip(strand1, strand2) if s1 != s2 ])
