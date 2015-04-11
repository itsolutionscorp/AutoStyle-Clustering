QUESTION = 'Sure.'
YELL = 'Woah, chill out!'
EMPTY = 'Fine. Be that way!'
DEFAULT = 'Whatever.'

def is_yelling(s):
    return any(x.isalpha() for x in s) and s.upper() == s

def hey(s):
    if s.strip() == '':
        return EMPTY
    elif is_yelling(s):
        return YELL
    elif s.endswith("?"):
        return QUESTION
    return DEFAULT
