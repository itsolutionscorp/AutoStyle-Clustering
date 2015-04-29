def transform(old):
    return {elem.lower(): key
            for key, value in old.items()
            for elem in value}
