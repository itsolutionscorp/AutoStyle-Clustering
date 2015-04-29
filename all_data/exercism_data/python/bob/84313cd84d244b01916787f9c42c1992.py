#
# Skeleton file for the Python "Bob" exercise.
#
import string

def hey(what):
    if what.strip() == '':                          #Blank message
        return 'Fine. Be that way!'
    elif what == what.upper() and hasLetters(what): #Shouting message
        return "Whoa, chill out!"
    elif what.strip()[-1] == '?':                   #Question
        return 'Sure.'
    else:                                           #Everything else
        return 'Whatever.'

def hasLetters(message):
    for char in message:
        if char in string.letters:
            return True
    return False
