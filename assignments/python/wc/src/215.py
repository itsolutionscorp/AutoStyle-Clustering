import string
def word_count(sentence):
    words = sentence.split()
    counter = {}
    for word in words:
        counter[word] = words.count(word)
    return counter
