def distance(first, second):
    dis = 0
    for a, b in zip(first,second):
        if a != b:
            dis += 1
    return dis
