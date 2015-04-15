# Bob python exercism, 3rd iteration
import string

def is_question(s):
    """ Checks if last (non-silent) character is a question mark """
    return string.rstrip(s).endswith('?')
    
def is_yelling(s):
    """ Checks if statement contains letters and if they are all caps """
    return s.upper() == s and any(c in string.ascii_lowercase for c in s.lower())

def hey(what):
    if string.rstrip(what) == "": #Test for prolonged silence.
        return 'Fine. Be that way!'
    elif is_yelling(what): # Test for yelling
        return 'Whoa, chill out!'
    elif is_question(what): #Test if Bob is being asked a question, but not being yelled at.
        return 'Sure.'
    else: # We are left with a non-yelled, non-question statement.
        return 'Whatever.'
