def distance(a, b):
    if len(a) == len(b):
        diff = [item for item in range(len(a)) if a[item].upper() != b[item].upper()]
        return len(diff)
