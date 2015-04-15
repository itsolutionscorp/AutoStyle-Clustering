#
# "hey" method for python "Bob" exercise.
#
def hey(what):
    if(what.isupper()):
        return "Whoa, chill out!"
    elif(what.rstrip().endswith('?')):
        return "Sure."
    elif(what.expandtabs().isspace() or not what ):
        return "Fine. Be that way!"
    else:
        return "Whatever."
