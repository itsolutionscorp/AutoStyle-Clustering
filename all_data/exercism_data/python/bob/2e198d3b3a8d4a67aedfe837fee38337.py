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

    if what.upper() == what and not what.join([i for i in what if i.isalpha()]) == "":
        return YELLING_RESPONSE

    if what.endswith("?"):
        return QUESTION_RESPONSE

    return DEFAULT_RESPONSE
