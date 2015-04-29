import re

def hey(phrase):
    if not phrase or not re.search(r'\w+', phrase):
        return 'Fine. Be that way!'
    if phrase == phrase.upper() and re.search(r'[a-zA-Z]+', phrase):
        return 'Woah, chill out!'
    if phrase[-1] == '?':
        return 'Sure.';
    return 'Whatever.'
