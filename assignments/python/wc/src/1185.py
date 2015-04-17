from collections import Counter
import string
import re
def word_count(words):
    return Counter(words.lower().translate(None, string.punctuation).split())
print word_count("GO GO go, go, GO!!! go GO GO no")
