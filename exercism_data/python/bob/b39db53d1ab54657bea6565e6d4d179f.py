#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    retstring = "Whatever."
    stripped = what.strip()
    
    if stripped == "":
        retstring = "Fine. Be that way!"
    elif (containsletters(stripped) and isAllcaps(stripped)):
        retstring = "Whoa, chill out!"
    elif stripped[len(stripped)-1:len(stripped)] == "?":
        retstring = "Sure."
    return retstring


def containsletters(_what):
    for letter in _what:
        if letter.isalpha():
            return True
    return False

def isAllcaps(_what):
    for letter in _what:
        if letter.islower():
            return False
    return True
