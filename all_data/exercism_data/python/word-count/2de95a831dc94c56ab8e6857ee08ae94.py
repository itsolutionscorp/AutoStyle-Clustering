def word_count(phrase):
    phrase = phrase.split()
    return dict([(s, phrase.count(s)) for s in set(phrase)])
