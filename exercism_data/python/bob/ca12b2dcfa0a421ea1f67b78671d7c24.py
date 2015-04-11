#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    # strip trailing right/left + \b \t \n \a \r
    what = what.rstrip().lstrip().strip('\btnar')

    #if everything upper case return whoa, chill out!
    if what.isupper():
        return "Whoa, chill out!"
    # if empty return
    elif not what:
        return "Fine. Be that way!"
    #when ending in a ? return "Sure"
    elif what.endswith("?"):
        return "Sure."
    else:
        return "Whatever."
