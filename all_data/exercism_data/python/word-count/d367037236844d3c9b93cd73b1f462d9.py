def word_count(sentence):
    result = {}
    for item in list(set(sentence.split())):
        result[item] = sentence.count(item)
    return result
