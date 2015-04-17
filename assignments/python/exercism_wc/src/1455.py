import itertools
def word_count(phrase):
    """Counts how many times each word repeats on the phrase
    :phrase: a string object
    :returns: a dict with the relation between the word and repetition.
    """
    return {
        word: len(list(occurrences)) for word, occurrences in
        itertools.groupby(sorted(phrase.strip().split()))
    }
