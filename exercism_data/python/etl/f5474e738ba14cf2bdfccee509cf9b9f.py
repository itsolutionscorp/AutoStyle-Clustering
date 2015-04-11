def transform(old):
    return {word.lower(): score for score, words in old.items() for word in words}
