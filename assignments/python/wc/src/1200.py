def word_count(phrase):
    """docstring for word_count"""
    words = phrase.split()
    result = {}
    for word in words:
        result[word] = words.count(word)
    return result
