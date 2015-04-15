from collections import Counter
import string

def word_count(input):

    # Strips punctuation
    nopunctuation = input.translate(string.maketrans("",""), string.punctuation)

    # Makes it all lowercase then splits into list of words
    wrdlst = nopunctuation.lower().split()

    return Counter(wrdlst)
