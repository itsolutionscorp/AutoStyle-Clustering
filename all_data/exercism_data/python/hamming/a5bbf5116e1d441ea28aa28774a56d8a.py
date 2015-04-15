__author__ = 'shandr'

def distance(a,b):
    count = 0
    distance = 0
    for l in a:
        if l != b[count]:
            distance += 1
        count += 1
    return distance
