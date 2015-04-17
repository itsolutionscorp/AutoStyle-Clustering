import re
def word_count(phrase):
    words = {}
    for w in re.split(r'[\s\n]+', phrase):
        words[w] = words.get(w, 0) + 1
    return words
