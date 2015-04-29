from string import punctuation

def word_count(phrase):
    counter = {}
    words = phrase.translate(None, punctuation).lower().split()
    for word in words:
        if word in counter:
            counter[word] = counter[word] + 1
        else:
            counter[word] = 1
    return counter
