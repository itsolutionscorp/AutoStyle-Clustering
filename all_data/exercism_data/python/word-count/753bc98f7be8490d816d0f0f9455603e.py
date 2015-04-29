import collections

def word_count(s):
    d = collections.defaultdict(lambda: 0)

    for x in filter(None, s.replace('\n', ' ').split(' ')):
        d[x] = d[x] + 1

    return dict(d)
