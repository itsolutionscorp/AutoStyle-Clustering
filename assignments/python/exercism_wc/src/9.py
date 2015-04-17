def word_count(text):
    count = dict()
    for word in text.split():
        count[word] = count.get(word, 0) + 1
    return count
