#
# Skeleton file for the Python "Bob" exercise.
#
# albntomat0 21 Feb 15
#
def hey(what):
    if len(what.rstrip()) == 0:
        return "Fine. Be that way!"
    elif what.upper() == what and any(let.isalpha() for let in what): 
        return "Whoa, chill out!"
    elif what.rstrip()[-1:] == "?":
        return "Sure."
    else:
        return "Whatever."
    return
