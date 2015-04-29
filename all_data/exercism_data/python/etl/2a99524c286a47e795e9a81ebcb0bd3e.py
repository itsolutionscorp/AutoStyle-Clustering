__author__ = 'jimblackler'


def transform(old):
    out = {}
    for k, v in old.items():
        for v2 in v:
            out[v2.lower()] = k
    return out
