def distance(source, target):
    diffs = (diff(i, source, target) for i in range(len(source)))
    return sum(diffs)


def diff(i, s, t):
    if s[i] == t[i]:
        return 0
    return 1
