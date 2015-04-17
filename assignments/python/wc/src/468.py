import re
def word_count(phrase):
    word_dict = {}
    for word in filter(None, re.split(r'[^a-zA-Z0-9]+', phrase)):
        word = word.lower()
        try:
            word_dict[word] += 1
        except KeyError:
            word_dict[word] = 1
    return word_dict
