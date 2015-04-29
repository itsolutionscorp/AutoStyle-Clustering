def word_count(sentence):
    words = sentence.split()
    count = {}
    for word in words:
        count[word] = count.get(word, 0) + 1
    return count
