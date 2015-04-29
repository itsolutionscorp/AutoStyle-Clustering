def distance(source, target):
    return sum(1 for (s,t) in zip(source,target) if s!=t)
