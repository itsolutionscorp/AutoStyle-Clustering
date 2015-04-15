#
# Skeleton file for the Python "Bob" exercise.
#

DEFAULT_RESPONSE = 'Whatever.'
YELLING_RESPONSE = 'Whoa, chill out!'
QUESTION_RESPONSE = 'Sure.'
NOTHING_RESPONSE = 'Fine. Be that way!'

def hey(what):
    
    what = what.strip()
    
    if len(what) == 0:
        return NOTHING_RESPONSE
    elif what.isupper():
        return YELLING_RESPONSE
    elif what.endswith("?"):
        return QUESTION_RESPONSE


    return DEFAULT_RESPONSE
