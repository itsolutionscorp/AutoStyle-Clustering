from string import ascii_letters
def word_count(text):
    """
    given a string, returns a dictionary.
    keys are the words in the text,
    values are counts of these words.
    """
    d = dict()
    for w in text.split():
        d[w] = d.get(w, 0) + 1 # dictionnary update
    return d
