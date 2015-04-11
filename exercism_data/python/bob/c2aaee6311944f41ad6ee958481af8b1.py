#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    #check if what is a string and if there is \t in what
    if not what or "\t" in what:
        return "Fine. Be that way!"
    #if what is upper we are screaming
    if what.isupper() == True:
        return "Whoa, chill out!"
    #if what stripped of spaces ends with ? that means we have a question
    if what.strip()[-1] == "?":
        return "Sure."
    #for anything else we don't care
    else:
        return "Whatever."
