def word_count(text):
    words = text.split()
    result = dict()
    for word in words:
        if word in result:
            result[word] += 1
        else:
            result[word] = 1
    return result
