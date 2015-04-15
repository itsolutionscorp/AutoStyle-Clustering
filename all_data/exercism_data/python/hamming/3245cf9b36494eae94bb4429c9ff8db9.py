def distance(first, second):
    counter = 0
    for a,b in zip(first,second):
        if a != b:
            counter += 1
    return counter
