# Bob
#
# exercism "bob" code challange
#
# Jan 7, 2015
# Joshua Ferdaszewski
#
def hey(what):
    
    # Strip any tailing whitespace characters
    said = what.rstrip()

    # count the number of upercase and lowercase letters
    up = 0
    low = 0
    for c in xrange(len(said)):
        if said[c].isupper():
            up += 1
        if said[c].islower():
            low += 1

    # Nothing is said
    if said == "":
        response = "Fine. Be that way!"

    # Yelling! (at lease one uppercase and no lowercase letters)
    elif up > 0 and low == 0:
        response = "Whoa, chill out!"

    # A question is asked (said ened with a '?')
    elif said[(len(said) - 1)] == "?":
        response = "Sure."

    # Default response
    else:
        response = "Whatever."

    return response
