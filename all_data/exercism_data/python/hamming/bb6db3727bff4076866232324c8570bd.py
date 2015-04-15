def distance(first, second):
    diff = 0 
    for i in range(len(first)): 
        if second[i] != first[i]: diff += 1
    return diff
