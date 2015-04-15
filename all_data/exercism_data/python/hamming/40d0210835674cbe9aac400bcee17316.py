def point(coda, codb):
    return int(coda != codb)


def distance(stra, strb):
    return sum(map(point, stra, strb))
