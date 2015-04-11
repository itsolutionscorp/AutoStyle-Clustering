def distance(sec1, sec2):
    counter = 0
    for (x, y) in zip(sec1, sec2):
        if x != y:
            counter += 1
    return counter
