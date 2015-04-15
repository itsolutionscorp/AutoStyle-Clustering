def word_count(mystr):
    result = {}

    for word in mystr.split():
        result[word] = result.get(word, 0) + 1

    return result
