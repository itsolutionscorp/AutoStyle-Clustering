from collections import Counter
import string
def word_count(input):
    nopunctuation = input.translate(string.maketrans("",""), string.punctuation)
    wrdlst = nopunctuation.lower().split()
    return Counter(wrdlst)
