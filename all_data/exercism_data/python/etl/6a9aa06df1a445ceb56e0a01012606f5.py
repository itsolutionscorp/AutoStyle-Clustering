def transform(old):
    new = {}

    for points, letters in old.items():
        for letter in letters:
            new[letter.lower()] = points

    return new
