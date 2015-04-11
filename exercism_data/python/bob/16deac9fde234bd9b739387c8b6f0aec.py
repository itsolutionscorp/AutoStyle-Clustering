#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #assume that Bob was yelled at if all caps
    if what.isupper():
        return "Whoa, chill out!" 
    #if all spaces or the message was empty, then nothing was actually said
    elif what.isspace() or len(what)==0:
        return "Fine. Be that way!"
    #if the last character is a question mark, then it was a question
    elif what[-1]=="?":
        return "Sure."
    #else, reply "Whatever."
    else:
        return "Whatever."
    
