def word_count(words):
    words = words.split()
    result = {}
    for word in set(words):
        result[word] = words.count(word)
    return result
