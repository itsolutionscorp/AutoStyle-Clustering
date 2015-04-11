from __future__ import unicode_literals

def is_empty(what):
    if what.strip() is '': return True

def is_question(what):
    last_character = what.strip()[-1]
    if last_character == '?': return True

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
