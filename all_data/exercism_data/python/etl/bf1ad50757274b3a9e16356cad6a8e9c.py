def transform(old):
    return { letter_or_word.lower(): score for score, word in old.iteritems() for letter_or_word in word }
