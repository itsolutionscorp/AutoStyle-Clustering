__author__ = 'shandr'

def distance(a,b):
    if len(a) != len(b):
        return -1
    count = 0
    distance = 0
    for l in a:
        if l != b[count]:
            distance += 1
        count += 1
    return distance
