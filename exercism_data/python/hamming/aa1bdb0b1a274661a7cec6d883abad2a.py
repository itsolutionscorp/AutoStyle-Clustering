def distance(w1, w2):
    return len(filter(lambda (x, y): x != y, zip(w1, w2)))
