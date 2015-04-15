# Exercise 21
# Skeleton file for the Python 'Bob' exercise.
#
import unicodedata

def hey(what):
    what = ''.join(c for c in unicodedata.normalize('NFD',what)
        if unicodedata.category(c) != 'Mn')
    what = what.rstrip()
    
    if len(what)==0:
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    if what.endswith('?'):
        return 'Sure.'
    
    return 'Whatever.'
