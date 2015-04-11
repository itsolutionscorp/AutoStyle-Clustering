#
# Models Behavior of Teenager, Bob, and provides canned responses to various strings, "what"
# Exercism.io Python Exercise 1 ("bob.py")
# Coded by Diane Remaley

def hey(what):

    #Check if yelling, only executes if there is a letter in there
    if what.upper() == what and any(c.isalpha() for c in what) : 
        return "Whoa, chill out!"
   

    #Check if question, removes trailing whitespace if needed
    if what.rstrip()[-1:] == '?' : return "Sure."


    #Check if empty, i.e.that 'what' doesn't contain any numbers or letters.  
    elif not any((c.isalpha() or c.isdigit()) for c in what) :
        return "Fine. Be that way!"

    # Normal response, catch all
    else : return "Whatever."
    
    return
