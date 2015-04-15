def distance(sa, sb):
    return len([1 for a, b in zip(sa, sb) if a != b])
