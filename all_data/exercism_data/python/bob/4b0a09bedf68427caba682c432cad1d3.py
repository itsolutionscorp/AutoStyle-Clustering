#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if(what.strip() == ""):
        return "Fine. Be that way!"
    elif(what.isupper()):
        return "Woah, chill out!"
    elif(what[-1] == "?"):
        return "Sure."
    else:
        return "Whatever."
