def transform(old):
    return {i.lower():key for key in old.keys() for i in old[key]}
