import re

def word_count(string):

    spaces = re.compile(r'[\s]', re.UNICODE)
    words = spaces.sub(' ', string).split(' ')  # Take any white space characters and replace them by a single space
    words = [w for w in words if not w is '']   # Remove leftover empty strings from regex replacement

    occurances = {}
    for word in words:
        if not word in occurances:
            occurances[word] = 0
        occurances[word]+=1
    return occurances
