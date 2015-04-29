def distance(val1, val2):
    outval = 0
    count = 0
    while count < len(val1):
        if val1[count] != val2[count]:
            outval += 1
        count+= 1
    return outval
