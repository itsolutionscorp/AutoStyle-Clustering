#
# Skeleton file for the Python "Bob" exercise.
#

QUESTION = '?'
EXCLAMATION = '!'

def hey(what):


    if what.isupper():
        return 'Whoa, chill out!'
    elif what.strip().endswith(QUESTION):
        return 'Sure.'
    elif not what.strip():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
