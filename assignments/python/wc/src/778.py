def word_count(input):
    result = {}
    for word in input.split():
        if word in result:
            result[word] += 1
        else:
            result[word] = 1
    return result
