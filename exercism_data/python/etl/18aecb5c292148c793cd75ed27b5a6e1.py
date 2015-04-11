def transform(old):
    new = dict()
    for score, letters in old.items():
        for letter in letters:
            new[letter.lower()] = score
    return new
