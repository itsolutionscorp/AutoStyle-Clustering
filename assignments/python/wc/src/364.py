def word_count(sentence):
    count = {}
    for word in sentence.split():
        try:
            count[word] += 1
        except KeyError:
            count[word] = 1
    return count
