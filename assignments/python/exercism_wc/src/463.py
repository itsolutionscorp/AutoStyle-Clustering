import re
NONALNUM = re.compile('[^\w]')
def word_count(words):
    splitwords = NONALNUM.sub(' ', words).lower().split()
    return dict([
        [word, splitwords.count(word)] for word in splitwords
    ])
