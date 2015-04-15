#
# Skeleton file for the Python "Bob" exercise.
#
import re
import unicodedata

def hey(what):
    #normalizing unicode characters to treat them all as generic lowercase/uppercase
    #since bob can't make that distinction
    what = unicodedata.normalize('NFD', what)
    #searching for question marks followed (or not) by empty string at the end of the string
    question = re.search('([?\s]+)$',what)
    allLowercase = re.search('[a-z]',what)
    allUppercase = re.search('[A-Z]',what)
    something = re.search('[a-zA-Z0-9]',what)
    emptiness = re.search('\s|',what)
    if not something and emptiness:
        return 'Fine. Be that way!'
    elif allUppercase and not allLowercase:
        return 'Whoa, chill out!'
    elif question:
        return 'Sure.'
    else:
        return 'Whatever.'
