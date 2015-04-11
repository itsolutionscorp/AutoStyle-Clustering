def distance(one, two):
    count = 0

    for (x, y) in zip(one, two):
        if x != y:
            count += 1

    return count
