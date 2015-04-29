import re

def word_count(text):
    words = re.compile("\s+").split(text)
    result = {}
    for word in words:
        result[word] = result.get(word, 0) + 1
    return result
