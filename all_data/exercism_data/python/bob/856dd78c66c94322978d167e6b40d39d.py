#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # If you shout at Bob
    if what.isupper():
        return "Whoa, chill out!"
    # If you ask Bob a question
    elif what[-1:] == "?":
        return "Sure." 
    # If you say nothing
    elif what.isspace() or what == '':
        return "Fine. Be that way!"
    # Everything else
    return "Whatever."
