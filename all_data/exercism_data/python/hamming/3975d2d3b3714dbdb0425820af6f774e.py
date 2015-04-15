def distance(a,b):
    return len([1 for i,j in zip(a,b) if i != j])
