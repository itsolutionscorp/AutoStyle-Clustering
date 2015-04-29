def transform(old):
    return {value.lower(): key for key, values in old.items() for value in values}
