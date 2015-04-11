def distance(start, end):
    return len(filter(None, map(lambda g:g[0] != g[1], zip(start,end))))
