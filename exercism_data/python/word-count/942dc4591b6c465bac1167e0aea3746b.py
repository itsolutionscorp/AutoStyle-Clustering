import string


def word_count(words):
    wc = {}
    words = words.translate(None, string.punctuation)
    words = words.lower()
    for word in words.split(' '):
        if word:
            wc[word] = wc.get(word, 0) + 1
    return wc
