#
# Skeleton file for the Python "Bob" exercise.
#
import string

def hey(what):

    question = what.strip()

    if len(question) is 0:
        return 'Fine. Be that way!'

    if any(c in string.letters for c in question) and question == question.upper():
        return 'Whoa, chill out!'
    elif question[-1] == '?':
        return 'Sure.'

    
    return 'Whatever.'
