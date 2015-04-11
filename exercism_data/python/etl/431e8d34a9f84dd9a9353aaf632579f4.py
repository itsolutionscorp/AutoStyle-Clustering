def transform(old):
    return {k.lower(): v
            for v in old.keys()
            for k in old[v]}
