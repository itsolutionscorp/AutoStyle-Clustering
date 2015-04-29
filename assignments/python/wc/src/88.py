def word_count(text):
    result = {}
    text = text.split()
    for word in text:
        result[word] = text.count(word)
    return result
