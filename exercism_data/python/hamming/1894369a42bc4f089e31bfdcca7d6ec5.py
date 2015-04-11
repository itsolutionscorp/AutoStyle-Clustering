def distance(orig, new):
    distance = 0
    for char in range(0,len(orig)):
        if orig[char] != new[char]:
            distance += 1
    return distance
