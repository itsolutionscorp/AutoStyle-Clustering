import itertools
def word_count(phrase):
    words = sorted(phrase.split())
    return {
        word: len(list(g))
        for word, g in itertools.groupby(words)
    }
