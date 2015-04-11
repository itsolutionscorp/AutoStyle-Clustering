#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    trimmed = what.strip()
    if is_empty(trimmed):
        return 'Fine. Be that way!'
    elif is_shouting(trimmed):
        return 'Whoa, chill out!'
    elif is_question(trimmed):
        return 'Sure.'
    else:
        return 'Whatever.'  

def is_question(what):
    return what.endswith('?') 

def is_empty(what):
    return what == ''

def is_shouting(what):
    return what.isupper()
