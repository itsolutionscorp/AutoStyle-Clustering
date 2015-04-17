def word_count(words):
    count = {}
    for word in words.split():
        count[word] = count.get(word, 0) + 1
    return count
