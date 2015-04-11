def distance(a, b):
    return len([(p, q) for p, q in zip(a, b) if p != q])
