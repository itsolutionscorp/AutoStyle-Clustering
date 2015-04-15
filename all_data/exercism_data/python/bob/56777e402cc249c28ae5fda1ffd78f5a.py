import re

def hey(what):
    """lackadisical teenager response function"""

    if re.search('^\s*$', what):
        answer = 'Fine. Be that way!'

    elif what.upper() == what and re.search('[A-Z]', what):
        answer = 'Whoa, chill out!'

    elif re.search("\?\s*$", what):
        answer = 'Sure.'

    else:
        answer = 'Whatever.'

    return answer
