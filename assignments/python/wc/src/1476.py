import re
def word_count(string):
    a=string.split()
    return { word:a.count(word) for word in set(a) }
