def word_count(phrase):
    words = phrase.split()
    result = {}
    for word in words:
        result[word] = 0
        for word2 in words:
            if word == word2:
                result[word] = result[word] + 1
    return result
