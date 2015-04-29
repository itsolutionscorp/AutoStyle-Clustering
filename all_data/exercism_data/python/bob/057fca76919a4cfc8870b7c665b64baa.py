#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

##  First, let's strip the trailing whitespace
    what = what.rstrip()

##  Yelling is ALL CAPS; Bob responds with "Whoa, chill out!"
    if what.isupper():
        return "Whoa, chill out!"

##  Questions end with a question mark; Bob responds with "Sure."
    elif what.endswith("?"):
        return "Sure."
 
##  Say nothing;  Bob responds with "Fine. Be that way!"
    elif what == "":
        return "Fine. Be that way!"

##  Say anything else;  Bob responds with "Whatever."
    
    return "Whatever."
