__author__ = 'ulvhamne'
def word_count(sentence):
    words = {}
    for word in sentence.split():
        if word in words:
            words[word] += 1
        else:
            words[word] = 1
    return words
