# Python Script
# Author: Adam Gausmann


def hey(what):
    if not what.strip():
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    if what[-1:] == '?':
        return 'Sure.'
    return 'Whatever.'
