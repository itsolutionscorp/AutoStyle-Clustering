import string
from collections import Counter
def word_count(wordlist):
    wordlist = wordlist.lower().translate(wordlist.maketrans('','',string.punctuation))
    words = wordlist.split()
    return Counter(words)
