import re
def word_count(string):
    return { word:string.split().count(word) for word in set(string.split()) }
