import re

def is_all_upper(what):
    no_digits = re.sub(r'\d',' ',what)
    return re.search(r'\w', no_digits, re.UNICODE) and what.upper() == what

def hey(what):
    what = what.strip()
    if what.is_upper():
        return 'Whoa, chill out!'
    if 0 == len(what):
        return 'Fine. Be that way!'
    if what.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
