def word_count(text):
    result = {}
    for word in text.split():
        if word in result.keys():
            result[word] += 1
        else:
            result[word] = 1
    return result
