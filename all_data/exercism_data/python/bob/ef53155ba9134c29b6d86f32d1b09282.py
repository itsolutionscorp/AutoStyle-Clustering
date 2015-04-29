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

    # Nothing is said
    if said == "":
        response = "Fine. Be that way!"

    # Yelling! (at lease one uppercase and no lowercase letters)
    elif said.isupper():
        response = "Whoa, chill out!"

    # A question is asked (said ended with a '?')
    elif said[-1] == '?':
        response = "Sure."

    # Default response
    else:
        response = "Whatever."

    return response
