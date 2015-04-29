import re

def word_count(phrase):
    '''Count the occurrences of each word in phrase'''
    word_list = re.findall(r'\w+', phrase.lower())
    return dict((word, word_list.count(word)) for word in word_list)
