#
# Skeleton file for the Python "Bob" exercise.
#
import re 

def allWhite(what):
    white = re.findall(r'\s',what)
    if len(white) - len(what) == 0:
        return True
    return False

def hey(what):
    if allWhite(what):
        return 'Fine. Be that way!'
        
    if what.isupper():
        return 'Whoa, chill out!'

    if what[-1] == '?':
        return 'Sure.'
    
    return 'Whatever.'
