#
# Skeleton file for the Python "Bob" exercise.
#

def is_shouting(what):
    letters = [letter for letter in what if letter.isalpha()]
    capital_letters = [letter for letter in letters if letter.isupper()]
    return len(letters)==len(capital_letters) and len(letters)>0
    
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
    else:
        return 'Whatever.'
