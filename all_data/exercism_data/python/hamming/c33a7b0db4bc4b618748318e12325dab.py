def distance(one, two):
    x = 0
    y = 0
    while x < len(one) and x < len(two):
        if one[x] != two[x]:
            y += 1
        x +=1
    return y
