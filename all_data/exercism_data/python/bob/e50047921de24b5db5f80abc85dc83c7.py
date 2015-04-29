#
# Skeleton file for the Python "Bob" exercise.
#

from re import match

#Look for at least a word
#If no words are present, treat it as a simple question
#Probably the regex should take into account other symbols @#-_, etc
def has_words(what):
    regex = r"^.*[a-zA-z]+.*$"
    m = match(regex, what)
    if m:
        return True
    return False


def shouting(what):
    if what == what.upper() and has_words(what):
        return True
    return False


def empty(what):
    if what == "" or what.isspace():
        return True
    return False

def hey(what):
    actual_what = what.strip(' ')
    
    if empty(actual_what):
        return 'Fine. Be that way!'
    
    #Question
    if actual_what[-1] == "?":
        #Shouting overrides question
        if shouting(actual_what):
            return 'Whoa, chill out!'
        return 'Sure.'
    
    #Shouting not ending in ?
    if shouting(actual_what):
        return 'Whoa, chill out!'
    
    
    return 'Whatever.'
