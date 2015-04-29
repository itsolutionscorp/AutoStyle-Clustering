def distance(one, two):
    comp = zip(one, two)
    count = 0
    for item in comp:
        if item[0] != item[1]:
            count += 1
    return count
