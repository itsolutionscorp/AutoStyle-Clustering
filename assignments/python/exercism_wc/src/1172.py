import string
def word_count(words):
    words = words.lower()\
        .translate(None, string.punctuation)\
        .split()
    word_list = dict()
    for word in words:
        if word in word_list:
            word_list[word] += 1
        else:
            word_list[word] = 1
    return word_list
