def is_empty(what):
    return not what or what.isspace()

def is_question(what):
    return what.strip().endswith('?')

def is_shouted(what):
    return what.isupper()

def hey(what):

    if is_empty(what):
        return "Fine. Be that way!"

    elif is_shouted(what):
        return "Whoa, chill out!"

    elif is_question(what):
        return "Sure."

    else:
        return "Whatever."
