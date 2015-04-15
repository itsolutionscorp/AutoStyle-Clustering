import re
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if is_null(what):
        return 'Fine. Be that way!' 
    if is_question(what):
        return "Sure."
    if is_yell(what):
            return "Whoa, chill out!"
    return "Whatever."

def has_word(what):
    if re.search("([a-zA-Z]+)", what):
        return True
    return False

def is_question(what):
    return what.endswith("?") and not is_yell(what)

def is_yell(what):
    return what.upper() == what and has_word(what)

def is_null(what):
    return what == ""
