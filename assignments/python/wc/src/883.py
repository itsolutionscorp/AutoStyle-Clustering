__author__ = 'Cedric Zhuang'
def word_count(s):
    word_map = {}
    for word in s.split():
        if word in word_map:
            word_map[word] += 1
        else:
            word_map[word] = 1
    return word_map
