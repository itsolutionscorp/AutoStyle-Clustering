__author__ = 'Greg'

def hey(sent):
    """
    sent is a string entered by the user, hypothetically spoken to a teenager named
    "Bob." Returns a string with Bob's answer.
    """
    if sent.strip() == "": # empty string after stripping
        return "Fine. Be that way!"
    
    elif sent.isupper(): # capslock'ed
        return "Woah, chill out!"

    elif sent.endswith('?'): # ends with a '?'
        return "Sure."
    
    else: # all other strings 
        return "Whatever." 
