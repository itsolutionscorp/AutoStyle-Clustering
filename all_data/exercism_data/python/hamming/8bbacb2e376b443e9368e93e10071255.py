def distance(first,second):
    #assume length is the same, from directions
    return sum([1 for (x,y) in zip(first,second) if x!=y])
