import string
from collections import Counter
def word_count(sentence):
    word_list = [x.strip(string.punctuation).lower()
                 for x in sentence.split()
                 if x.strip(string.punctuation).lower().isalpha()
                 or x.strip(string.punctuation).lower().isdigit()]
    return Counter(word_list)
