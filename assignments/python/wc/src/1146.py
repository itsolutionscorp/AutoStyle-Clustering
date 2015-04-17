def word_count(string):
    count = {}
    for word in string.split():
        count[word] = count.get(word, 0) + 1
    return count
