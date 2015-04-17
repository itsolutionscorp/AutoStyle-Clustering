__author__ = 'Micha'
def word_count(words):
    wordcount = {}
    for word in words:
        if word not in wordcount:
            wordcount[word] = 1
        else:
            wordcount[word] += 1
