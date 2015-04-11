def distance(one, two):

    count = 0
    for i in range(len(one)):
        if one[i] != two[i]:
            count += 1

    return count
