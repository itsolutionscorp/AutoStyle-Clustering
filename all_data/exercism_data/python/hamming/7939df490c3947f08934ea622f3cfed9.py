hamming = lambda a, b: len(filter(lambda (x, y): not x==y, map(None, a, b)))
