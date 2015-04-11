#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if shouting(what):
        reply = "Whoa, chill out!"
    elif asking(what):
        reply = "Sure."
    elif never_mind(what):
        reply = "Fine. Be that way!"
    else:
        reply = "Whatever."

    return reply

def shouting(what):
    return has_characters_all_upper(what)

def has_characters_all_upper(what):
    chars = "".join([c for c in what if c.isalpha()])
    return chars.isupper()

def asking(what):
    return len(what) > 0 and what[-1] == "?"

def never_mind(what):
    return what.isspace() or what == ""
        
