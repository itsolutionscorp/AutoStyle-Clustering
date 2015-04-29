#


def word_count(sentence):
    sentence = sentence.strip().split()
    words = {}
    for word in sentence:
        try:
            words[word] += 1
        except KeyError:
            words[word] = 1
    return words
