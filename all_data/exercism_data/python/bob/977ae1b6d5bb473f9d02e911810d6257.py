#
# Skeleton file for the Python "Bob" exercise.
#

def is_shouting(what):
    return what.isupper()
    
def is_a_question(what):
    return what.endswith('?')
    
def is_silence(what):
    return not what
        
def hey(what):
    what =  what.strip()
    if is_silence(what):
        return 'Fine. Be that way!'
    elif is_shouting(what):
        return 'Whoa, chill out!'
    elif is_a_question(what):
        return 'Sure.'
    return 'Whatever.'
