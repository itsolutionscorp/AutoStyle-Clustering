""" Exercise 3 """
import string
def word_count(phrase):
    """ Take a string, return the frequency of words inside """
    exclude = set(string.punctuation)
    phrase = ''.join(char for char in phrase if char not in exclude)
    word_list = phrase.lower().split(' ')
    frequency = [[x, word_list.count(x)] for x in set(word_list) if x != '']
    return dict(frequency)
