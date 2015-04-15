#
# Skeleton file for the Python "Bob" exercise.
#
# albntomat0 21 Feb 15
#
def hey(what):
    what = what.rstrip()
    if what == "":
        return "Fine. Be that way!"
    elif what.upper() == what and any(let.isalpha() for let in what): 
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return "Sure."
    return "Whatever."
