def distance(x, y):
    diff = [value for index, value in enumerate(x) if x[index] != y[index]]
    return len(diff)


def another_distance(x, y):
    count = 0
    for index, _ in enumerate(x):
        if x[index] != y[index]:
            count += 1
    return count
