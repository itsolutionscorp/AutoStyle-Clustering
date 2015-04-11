def transform(old):
    new = {}
    for points, letter_string in old.items():
        for letter in letter_string:
            new[letter.lower()] = points
    return new
