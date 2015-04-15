import re


def hey(message):
    if message.isupper():
        return 'Whoa, chill out!'
    elif message.endswith("?"):
        return 'Sure.'
    elif re.search(r'^\s*$', message):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
