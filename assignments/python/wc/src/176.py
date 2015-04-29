def word_count(phrase):
    result = {}
    for word in phrase.split():
        result[word] = result.get(word, 0) + 1
    return result
