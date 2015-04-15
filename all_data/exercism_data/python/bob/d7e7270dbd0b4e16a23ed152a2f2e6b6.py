import re

def hey(message):
    if re.search('^\s*$', message):
        return 'Fine. Be that way!'
    elif message.upper() == message and re.search('[A-Z]', message):
        return 'Whoa, chill out!'
    elif re.search('\?$', message):
        return "Sure."
    else:
        return 'Whatever.'
