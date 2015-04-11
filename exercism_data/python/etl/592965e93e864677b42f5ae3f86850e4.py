def transform(old):
    new = {}
    for score, chars in old.items():
        for char in chars:
            new[char.lower()] = score
    return new
