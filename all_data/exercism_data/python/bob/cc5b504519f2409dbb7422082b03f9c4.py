def __is_a_question__(s):
    return s.endswith("?")

def __is_shouting__(s):
    return s.isupper()

def __is_blank__(s):
    return s.strip() == ""

def hey(what):
    if __is_shouting__(what):
        return "Whoa, chill out!"
    elif __is_blank__(what):
        return "Fine. Be that way!"
    elif __is_a_question__(what):
        return "Sure."
    else:
        return "Whatever."
