def distance(first,second):
    hamminess = 0
    #assume length is the same, from directions
    for (x,y) in zip(first,second):
        if(x != y):
            hamminess += 1
    return hamminess
