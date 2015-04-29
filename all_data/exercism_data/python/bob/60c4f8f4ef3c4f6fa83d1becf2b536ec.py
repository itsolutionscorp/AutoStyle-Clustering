#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
   
    whatever = "Whatever."
    sure = "Sure."
    chill = "Whoa, chill out!"
    fine = "Fine. Be that way!"
    if not what or what.isspace():
        return fine 
    if what.isupper():
        return chill
    if what[-1] == '?' or what[-1].isspace():
        return sure
    return whatever
