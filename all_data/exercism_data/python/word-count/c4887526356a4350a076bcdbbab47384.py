from collections import Counter
import string
import re

#exclude = set(string.punctuation)
##table = string.maketrans("","")

def word_count(words):
    #words = words.lower().translate(None, string.punctuation) 
    return Counter(words.lower().translate(None, string.punctuation).split())
        

print word_count("GO GO go, go, GO!!! go GO GO no")
