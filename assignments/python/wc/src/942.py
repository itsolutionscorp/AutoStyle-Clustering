def word_count(text):
    words = text.split()
    result = {}
    for word in words:
        result[word] = result.get(word, 0) + 1
    return result
