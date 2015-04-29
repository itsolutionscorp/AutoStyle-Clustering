import string

def hey(what):

    what = what.strip();
    strlen = len(what)
    
    # answer empty questions with 'Fine. Be that way!'
    if strlen == 0:
        return "Fine. Be that way!"

    # if all letters capitalized, then yelling.  answer 'Whoa, chill out!'
    # he responds to yelling before bothering to check if it is a question or not.    
    hasalpha = False
    allupper = True
    for char in what:
        if char.isalpha ():
            hasalpha = True
            if char.upper() != char:
                allupper = False
        
    if hasalpha and allupper:
        return "Whoa, chill out!"

    # questions end with "?", answer 'Sure.'
    if what[strlen -1] == "?":
        return "Sure."

    # all others return 'whatever.'
    return "Whatever."
