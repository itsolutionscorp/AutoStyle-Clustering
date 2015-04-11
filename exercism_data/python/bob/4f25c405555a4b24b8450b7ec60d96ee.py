QUESTION = 'Sure.'
YELL = 'Woah, chill out!'
EMPTY = 'Fine. Be that way!'
DEFAULT = 'Whatever.'

def hey(s):
    if s.strip() == '':
        return EMPTY
    elif s.isupper():
        return YELL
    elif s.endswith("?"):
        return QUESTION
    return DEFAULT
