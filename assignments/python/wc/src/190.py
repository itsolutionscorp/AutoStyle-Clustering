import re
def word_count(phrase):
    result = {}
    for word in re.split('\s*', phrase):
        if word in result:
            result[word] += 1
        else:
            result[word] = 1
    return result
