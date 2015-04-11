__author__ = 'ulvhamne'
def word_count(words):
    words = words.split()
    dict = {}
    for word in words:
        if word in dict:
            dict[word] += 1
        else:
            dict[word] = 1
    return dict
