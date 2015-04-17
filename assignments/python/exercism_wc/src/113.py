def word_count(sentence):
    count = dict()
    words = sentence.split()
    for word in words:
        current = count.get(word, 0)
        count[word] = current + 1
    return count
