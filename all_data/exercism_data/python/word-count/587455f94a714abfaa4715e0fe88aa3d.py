"""Word Count function: counts the number of words in an input phrase
Input: a string with words separated by blank space
Returns: a dictionary with words as the key and number of occurences as the values"""
from collections import Counter

def word_count(input_string):
    return Counter(input_string.split())
            
