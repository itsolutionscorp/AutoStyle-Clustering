import re

def hey(what):
    onlydigits = re.match(r'.*\d', what) and not re.match(r'.*[A-Za-z]', what)
    question = what.strip().endswith('?')
    shouting = what.upper() == what and not onlydigits
    silence = not what.strip()
    statement = what.strip().endswith('.')
    if silence:
        return 'Fine. Be that way!'
    if question and not shouting:
        return 'Sure.'
    if shouting:
        return 'Whoa, chill out!'
    return 'Whatever.'
