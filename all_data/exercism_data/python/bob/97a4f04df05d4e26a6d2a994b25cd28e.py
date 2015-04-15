import re

def hey(what):
    alpha = re.compile('[a-zA-Z]')
    blank = re.compile(r'^\s*$')
    question = re.compile(r'\?\s*$')

    if blank.search(what) is not None:
        return 'Fine. Be that way!'
    if alpha.search(what) is not None and what.upper() == what:
        return 'Whoa, chill out!'
    if question.search(what) is not None:
        return 'Sure.'
    return 'Whatever.'
