def distance(a,b):
    return sum([0 if i == j else 1 for i,j in zip(a,b)])
