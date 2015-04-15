import re

def hey(text):
    if text.strip() == '':
        return 'Fine. Be that way!'
    if text.upper() == text and re.search('[a-zA-Z]', text):
        return 'Whoa, chill out!'
    if text[-1:] == '?':
        return 'Sure.'



    return 'Whatever.'
