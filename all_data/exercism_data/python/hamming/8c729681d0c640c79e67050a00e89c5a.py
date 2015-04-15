def distance(what1,what2):
    
    distance = 0
    if len(what1) == len(what2):
        for i in range(0,len(what1)):
            if not what1[i] == what2[i]: distance = distance + 1
    else: return -1
    return distance
