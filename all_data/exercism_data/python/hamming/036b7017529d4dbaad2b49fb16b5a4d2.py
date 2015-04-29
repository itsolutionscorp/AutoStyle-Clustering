def distance(strand1, strand2):
    try:
        return len([x for x, y in zip(strand1, strand2) if x != y and len(strand1) == len(strand2)])
    except:
        raise AssertionError
