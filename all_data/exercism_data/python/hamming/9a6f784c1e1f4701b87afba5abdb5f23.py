def distance(a, b):
    distance = 0
    length = len(a) if len(a) < len(b) else len(b)

    for i in range(0,length):
        distance += (a[i] != b[i])
    
    return distance
