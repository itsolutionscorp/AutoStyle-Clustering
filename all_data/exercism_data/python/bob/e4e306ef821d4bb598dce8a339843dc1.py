from __future__ import unicode_literals

import re

def hey(words):
    if words.strip() == '':
        return "Fine. Be that way!"
    if words.upper() == words and words.lower() != words:
        #alphabetical chars exists
        return "Woah, chill out!"
    if words.strip()[-1] == '?':
        return "Sure."
    return "Whatever."
