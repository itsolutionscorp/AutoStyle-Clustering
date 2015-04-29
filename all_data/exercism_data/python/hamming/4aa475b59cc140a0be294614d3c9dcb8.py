def distance(base, other):
    size = min(len(base), len(other))
    return sum(base[i] != other[i] for i in range(size)) + abs(len(base) - len(other))
