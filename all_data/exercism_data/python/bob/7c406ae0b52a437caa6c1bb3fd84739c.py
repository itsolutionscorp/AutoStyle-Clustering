"""
provides hey function that responds to limited request types
"""

def hey(phrase):
    """
    args:
        phrase = string of phrase for function to respond to

     returns:
        canned response of a lackadaisical teenager as a string
    """
    if phrase.isspace() or not phrase:
        return "Fine. Be that way!"
    elif phrase.isupper():
        return "Whoa, chill out!"
    elif phrase.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
    
