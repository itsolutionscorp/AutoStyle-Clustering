#
# Skeleton file for the Python "Bob" exercise.
#

QUESTION = '?'

def hey(what):
    what = what.strip()
    if what.isupper():  # yell
        return 'Whoa, chill out!'
    elif what.endswith(QUESTION):   # question 
        return 'Sure.'
    elif not what:  # not saying anythin
        return 'Fine. Be that way!'
    else:   # anything else
        return 'Whatever.'
