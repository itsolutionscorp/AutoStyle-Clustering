def distance(a,b):
    return len([(x,y) for x,y in zip(a,b) if x!=y]) if len(a) == len(b) else None
