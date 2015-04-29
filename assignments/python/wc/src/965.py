from re import sub
from collections import Counter
def word_count(s):
    '''Returns dictionary of words in sentence s and their respective counts.
    Words are alphanumeric and lowercase and separated by whitespace.''' 
    wordlist = sub(r'[^\w\s]+', '', s).lower().split()
    return Counter(wordlist)
