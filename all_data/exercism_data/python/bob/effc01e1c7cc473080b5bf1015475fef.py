def is_a_question(s):
    return s[-1] == "?"

def is_shouting(s):
    return s.isupper()

def is_blank(s):
    return s.strip() == ""

def hey(what):
    if is_shouting(what):
        return "Whoa, chill out!"
    elif is_blank(what):
        return "Fine. Be that way!"
    elif is_a_question(what):
        return "Sure."
    else:
        return "Whatever."
