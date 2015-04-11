import re

def word_count(phrase):
    '''Count the occurrences of each word in phrase'''
    l = re.findall(r'\w+', phrase.lower())
    return dict((word, l.count(word)) for word in l)
