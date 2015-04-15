def transform(old):
    new = {}
    for (old_key, old_value) in old.items():
        for new_key in old_value:
            new[new_key.lower()] = old_key
    return new
