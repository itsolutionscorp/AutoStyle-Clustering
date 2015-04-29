def transform(old):
    collector = {}
    for score, words in old.items():
        for word in words:
            collector[word.lower()] = score
    return collector
