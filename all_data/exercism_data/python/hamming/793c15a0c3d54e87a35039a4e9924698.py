def distance(s1, s2):
    match = set(zip(s1, s2))
    base  = set(zip('GCAT', 'GCAT'))
    return len(match - base)
