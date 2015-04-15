""" Exercise 3 """
import string

def word_count(phrase):
    """ Take a string, return the frequency of words inside """

    # Strip the incoming phrase of punctuation
    exclude = set(string.punctuation)
    phrase = ''.join(char for char in phrase if char not in exclude)

    # Split the phrase into words and calculate the frequency
    word_list = phrase.lower().split(' ')
    frequency = [[x, word_list.count(x)] for x in set(word_list) if x != '']
    return dict(frequency)
