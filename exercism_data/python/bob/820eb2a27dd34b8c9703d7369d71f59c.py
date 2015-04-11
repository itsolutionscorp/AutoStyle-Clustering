#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what[:-1]=="?":
        answear="Sure"
    elif what.isupper():
        answear="Whoa, chill out!"
    elif what == "":
        answear="Fine. Be that way"
    else :
        answear="Whatever"
    return what
