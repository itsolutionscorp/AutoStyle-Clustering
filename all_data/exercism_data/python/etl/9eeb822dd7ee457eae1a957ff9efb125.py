def transform(d):
    return {v.lower(): k
            for (k, l) in d.items()
            for v in l}
