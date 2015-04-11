#
# Skeleton file for the Python "Bob" exercise.
#

# Bob answers 'Sure.' if you ask him a question.
# He answers 'Whoa, chill out!' if you yell at him.
# He says 'Fine. Be that way!' if you address him without actually saying anything.
# He answers 'Whatever.' to anything else.

import unicodedata
import string

def remove_accents(data):
    return ''.join(x for x in unicodedata.normalize('NFKD', data) if x in string.ascii_letters)

def hey(what):
    
    #DEAL WITH UMLAUT INPUT - REPLACE ACCENT CHARS USING REMOVE_ACCENTS
    weirdchars = 0
    for i in what: 
        if ord(i)>=128: weirdchars = 1
    capswords = 0
    lowerwords = 0
    if weirdchars:
        for i in what.split():
            j = remove_accents(i)
            if j.isupper():
                capswords = capswords + 1
            else:
                lowerwords = lowerwords + 1
        if capswords>lowerwords: return "Whoa, chill out!"
        else: return "Whatever."

    #CHECK FOR SHOUTING - IGNORE NUMERIC WORDS
    capswords = 0
    lowerwords = 0
    for j in what.split():
        j = j.strip(',')
        if not j.isnumeric():
            if j.isupper():
                capswords = capswords + 1
            else:
                lowerwords = lowerwords + 1
    if capswords>lowerwords: return "Whoa, chill out!"

    if what.endswith('?'): return "Sure."
    
    if what.isspace() or what == '': return "Fine. Be that way!"

    return "Whatever."
