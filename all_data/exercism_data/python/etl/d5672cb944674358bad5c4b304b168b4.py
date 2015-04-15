def transform(oldscore):
    return {l.lower(): s for s,letters in oldscore.iteritems() for l in letters}
