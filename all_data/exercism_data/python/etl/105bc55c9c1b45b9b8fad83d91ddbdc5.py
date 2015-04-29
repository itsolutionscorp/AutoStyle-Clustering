def transform(old):
    return {v.lower(): key for key, values in old.items() for v in values}
