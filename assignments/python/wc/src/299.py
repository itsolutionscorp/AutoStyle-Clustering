def word_count(phrase):
    from string import punctuation
    phrase = list(phrase.lower().translate(None, punctuation).strip().split())
    x = {}
    for item in phrase:
        x[item] = phrase.count(item)
    return x
