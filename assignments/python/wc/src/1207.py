__author__ = 'Hinek'
def word_count(text):
    result = {}
    text = text.split()
    for word in text:
        if word not in result:
            result[word] = 0
        result[word] += 1
    return result
