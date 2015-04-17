'''This module takes a string input and outputs the word count for each word.
    Lesson: Learned about Counter in collections library
'''
from collections import Counter
def word_count(phrase):
    return Counter(phrase.split())
