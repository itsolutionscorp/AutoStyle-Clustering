def distance(X, Y):
    assert len(X) == len(Y), "Lengths of inputs don't match"
    return sum([x != y for x,y in zip(X,Y)])
