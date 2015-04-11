def distance(what1,what2):
    
    #WORKS:
    #distance = 0
    #if len(what1) == len(what2):
    #    for i in range(0,len(what1)):
    #        if not what1[i] == what2[i]: distance = distance + 1
    #else: return -1
    #return distance

    return sum([x != y for x, y in zip(what1, what2)])
