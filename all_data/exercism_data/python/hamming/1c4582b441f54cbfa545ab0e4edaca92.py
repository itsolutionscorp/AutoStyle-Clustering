def distance(data1, data2):
    diff = 0
    for x in range(0, len(data1)):
        if data1[x] != data2[x]:
            diff += 1
    return diff
