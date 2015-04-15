def transform(old):
    return {char.lower(): score for score, letters in old.iteritems() for char in letters}
