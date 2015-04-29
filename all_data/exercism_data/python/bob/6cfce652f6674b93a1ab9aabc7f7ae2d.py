#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    yelling = "Whoa, chill out!"
    statement = "Whatever."
    question = "Sure."
    blank = "Fine. Be that way!"
    
    # Removing extraneous whitespace
    what = what.strip()
    
    # Whitespace-only responses are reduced to empty string by strip
    if not what or what is None:
        return blank
        
    # Yelling is signified by all-uppercase; the uppercase test returns a false
    # pos if 'what' is entirely digits. Include a test to eliminate digit-only
    # responses.
    if what.isupper():
        return yelling
    
    # Questions are signified by a question mark in the last position.
    if what[-1] == "?":
        return question
 
    # If not yelling, not blank, and not a question, default to statement.    
    return statement
