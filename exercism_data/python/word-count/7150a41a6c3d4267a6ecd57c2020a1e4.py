def word_count(phrase):
    words = phrase.split()
    result = {}
    for word in words:
        if word in result:
            result[word] += 1
        else:
            result[word] = 1
    return result
