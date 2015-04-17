import string
def word_count(countme):
    word_num = {}
    for word in countme.split():
        word_num[word] = countme.split().count(word)
    return word_num
