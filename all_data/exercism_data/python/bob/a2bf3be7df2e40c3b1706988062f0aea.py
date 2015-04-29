#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    trimWhat = what.strip()
    lastchar =  trimWhat[-1:]
    if(lastchar == "?" and (not trimWhat.isupper())):
        return "Sure."
    
    elif trimWhat.isupper():
        return "Whoa, chill out!"
    
    elif trimWhat == "":
        return "Fine. Be that way!"

    else:
        return "Whatever."
