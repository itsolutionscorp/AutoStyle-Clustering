def Binary(s):
    try:
        v = 0
        for c in s:
            v *= 2
            v += {"0": 0, "1": 1}[c]
        return v
    except KeyError:
        return 0
