def transform(old):
    return dict((char.lower(), score) for score, letters in old.iteritems() for char in letters)
