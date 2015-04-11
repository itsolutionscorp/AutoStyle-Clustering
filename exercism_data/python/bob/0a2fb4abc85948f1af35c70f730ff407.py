def _is_a_question(s):
    return s.endswith("?")

def _is_shouting(s):
    return s.isupper()

def _is_blank(s):
    return s.strip() == ""

def hey(what):
    if _is_shouting(what):
        return "Whoa, chill out!"
    elif _is_blank(what):
        return "Fine. Be that way!"
    elif _is_a_question(what):
        return "Sure."
    else:
        return "Whatever."
