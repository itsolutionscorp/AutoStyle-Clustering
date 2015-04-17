import json
class Dictionary(object):
    pass
def word_count(phrase):
    words = phrase.split()
    result = Dictionary()
    for word in words:
        if hasattr(result, word):
            result.__dict__[word] += 1
        else:
            result.__dict__[word] = 1
    return result.__dict__
