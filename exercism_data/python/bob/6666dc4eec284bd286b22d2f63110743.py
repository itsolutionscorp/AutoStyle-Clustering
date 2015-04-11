#
# Skeleton file for the Python "Bob" exercise.
#

def is_question(what):
    return what[-1] == '?'
    
def is_yelling(what):
    return what.isupper()
    
def is_empty(what):
    return not what.strip()

def hey(what):
    if is_yelling(what):
        return 'Whoa, chill out!'
    elif is_empty(what):
        return 'Fine. Be that way!'
    elif is_question(what):
        return 'Sure.'
    else:
        return 'Whatever.'
