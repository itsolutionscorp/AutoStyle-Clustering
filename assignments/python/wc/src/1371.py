import string
def word_count(phrase):
    for c in string.punctuation:
        phrase.replace(c, '')
    words = phrase.split()
    counter = {}
    for word in words:
        if word not in counter:
            counter[word] = 1
        else:
            counter[word] += 1
    return counter
