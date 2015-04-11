# word counting function

from string import ascii_letters

def word_count(text):
    """
    given a string, returns a dictionary.
    keys are the words in the text,
    values are counts of these words.
    """
    d = dict()
    for w in text.split():
##        w = "".join(c for c in w if c in ascii_letters).lower()
        # removes punctuation, and makes case-insensitive
        d[w] = d.get(w, 0) + 1 # dictionnary update
    return d
