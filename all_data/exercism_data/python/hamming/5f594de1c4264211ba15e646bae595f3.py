def distance(*strands):
    i = 0
    for pair in zip(*strands):
        if not pair[0] == pair[1]:
            i += 1
    return i
