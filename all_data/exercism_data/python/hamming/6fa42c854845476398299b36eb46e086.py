def distance(a, b):
    if len(a) != len(b):
        raise Exception('Argument lengths differ.')

    count = 0
    for x in zip(a, b):
        if x[0] != x[1]:
            count += 1
    return count
