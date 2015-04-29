def distance(source, target):
    source = source.strip()
    target = target.strip()

    count = 0

    for pair in zip(source, target):
        if pair[0] is not pair[1]:
            count = count + 1

    return count
