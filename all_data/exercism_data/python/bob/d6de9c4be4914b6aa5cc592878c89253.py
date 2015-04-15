#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #if (what.find("!", -1) > 0 and what.isupper()) or (what.isupper() and what.find("?", -1 ) > 0):
    if what.isupper():
        response = "Whoa, chill out!"
    elif what.find("?", -1) > 0:
        response = "Sure."
    elif what.strip() == '':
        response = "Fine. Be that way!"
    else:
        response = "Whatever."
        
    return response
