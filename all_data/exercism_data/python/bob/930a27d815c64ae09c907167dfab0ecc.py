import string
import re

def hey(words):
    # remove whitespace characters
    words = string.join(words.split(), '')

    if not words:
        return "Fine. Be that way!"
    elif string.upper(words) == words and re.search('[A-Z]', words):
        return "Whoa, chill out!"
    elif words[len(words) - 1] == '?':
        return "Sure."
    else:
        return "Whatever."
