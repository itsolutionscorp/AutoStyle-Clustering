def all_space(s):
    return all([c.isspace() for c in s])

def some_letter(s):
    return any([c.isalpha() for c in s])

def all_caps(s):
    return s == s.upper()

def hey(what):
    if all_space(what):
        return 'Fine. Be that way!'
    if all_caps(what) and some_letter(what):
        return 'Woah, chill out!'
    elif what.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
