__author__ = 'Dalton'
import re
from collections import Counter
def word_count(sentence):
    sentence = re.sub('\W', ' ', sentence.lower()) #removes everything that's not a letter or number
    sentence = sentence.split() #removes whitespace
    return Counter(sentence) #creates and returns dictionary with all words and values
