import re

def hey(sentence):
    if not re.match('.*\w', sentence):
        return 'Fine. Be that way!'
    elif sentence.isupper():
        return 'Whoa, chill out!'
    elif sentence.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
