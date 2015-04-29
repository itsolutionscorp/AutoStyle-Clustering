#
# Skeleton file for the Python "Bob" exercise.
#
def is_yelling(what):
    return what.isupper() and not what.islower()

def is_nothing(what):
    return what.strip() == ""

def is_question(what):
    return what.endswith("?")

def hey(what):
    if is_yelling(what):
        return "Whoa, chill out!"
    if is_nothing(what):
        return "Fine. Be that way!"
    if is_question(what):
        return "Sure."
    return "Whatever."
   
