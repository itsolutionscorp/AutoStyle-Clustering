def distance(s1, s2):
    s1 = list(s1)
    s2 = list(s2)
    counter = 0
    for i in range(0,len(s1)):
        if s1[i] != s2[i]:
            counter += 1
        else:
            continue
    
    return counter
