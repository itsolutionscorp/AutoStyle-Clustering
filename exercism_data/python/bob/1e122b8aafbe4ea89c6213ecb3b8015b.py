#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        response = "Whoa, chill out!"
    elif what.strip().find("?", -1) > 0:
        response = "Sure."
    elif not what.strip():
        response = "Fine. Be that way!"
    else:
        response = "Whatever."
        
    return response