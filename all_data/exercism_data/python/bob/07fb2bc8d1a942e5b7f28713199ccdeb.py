#
# Skeleton file for the Python "Bob" exercise.
#

def is_shouting(what):
    return what.isupper()
    
def is_a_question(what):
    return what[-1]=='?' if len(what)>0 else False
    
def is_silence(what):
    return len(what)==0
    
def hey(what):
    what =  what.strip()
    if is_silence(what):
        return 'Fine. Be that way!'
    elif is_shouting(what):
        return 'Whoa, chill out!'
    elif is_a_question(what):
        return 'Sure.'
    return 'Whatever.'
