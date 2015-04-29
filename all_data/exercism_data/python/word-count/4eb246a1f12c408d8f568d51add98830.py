def word_count(sentence):

    words = {}

    for word in sentence.split():
        if words.has_key(word):
            words[word] += 1
        else:
            words[word] = 1

    return words
