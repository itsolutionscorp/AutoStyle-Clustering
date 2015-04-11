def word_count(phrase):
    result = {}
    words = phrase.split()
    for word in words:
        result[word] = words.count(word)
    return result
