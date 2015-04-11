def distance(source, target):
    return sum([0 if s == t else 1 for (s,t) in zip(source,target)])
