import string
def word_count(words):
    words = words.translate(None, string.punctuation)\
        .split()
    word_list = dict()
    for word in words:
        try:
            word_list[word.lower()] += 1
        except KeyError:
            word_list[word.lower()] = 1
    return word_list
