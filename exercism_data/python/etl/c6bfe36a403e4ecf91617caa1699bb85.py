def transform(old):
    new = {}
    for score, letter_list in old.items():
        for letter in letter_list:
            new[letter.lower()] = score
    return new
