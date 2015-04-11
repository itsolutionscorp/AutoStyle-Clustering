def transform(old):
    return {item.lower():key for key in old for item in old[key]}
