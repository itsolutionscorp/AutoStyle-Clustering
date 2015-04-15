def transform(old):
    return {v.lower(): k for k in old for v in old[k]}
