def distance(one, two):
    difference = 0;
    for n1, n2 in zip(one, two):
        if n1 != n2:
            difference += 1
    return difference
