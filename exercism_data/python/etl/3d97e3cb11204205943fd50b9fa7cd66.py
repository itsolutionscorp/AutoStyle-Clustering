from collections import defaultdict
def transform(old):
    out = {}
    for key in old:
        for value in old[key]:
            out[value.lower()] = key
    return out
