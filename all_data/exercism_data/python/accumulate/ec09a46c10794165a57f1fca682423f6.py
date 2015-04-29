def accumulate(items, transform):
    return [transform(item) for item in items]
