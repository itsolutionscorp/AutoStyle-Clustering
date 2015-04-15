from __future__ import unicode_literals

def hey(words):
    if words.strip() == '':
        return "Fine. Be that way!"
    if words.isupper():
        #alphabetical chars exists
        return "Whoa, chill out!"
    if words.endswith('?'):
        return "Sure."
    return "Whatever."
