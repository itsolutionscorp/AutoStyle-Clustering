def is_empty(what):
    return not what

def is_yell(what):
    return what.isupper()

def is_question(what):
    return what.endswith("?")

ANSWER_MAP = [(is_empty, "Fine. Be that way!"),
              (is_yell, "Whoa, chill out!"),
              (is_question, "Sure.")]

def hey(what):
    what = what.strip()
    return next((answer for test, answer in ANSWER_MAP if test(what)),
                "Whatever.")
