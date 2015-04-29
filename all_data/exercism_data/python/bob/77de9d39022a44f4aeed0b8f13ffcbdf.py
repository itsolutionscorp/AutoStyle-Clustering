import re

def hey(text):
    if len(text.strip()) == 0:
        return 'Fine. Be that way!'
    elif text.upper() == text and re.search('[a-zA-Z]', text):
        return 'Whoa, chill out!'
    elif text[-1:] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
