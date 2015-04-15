import re

def check_string(sentence):
    """#BOB"""
    if not sentence:
        return 'Whatever.'
    elif re.search('\?',sentence):
        return 'Fine. Be that way!'
    elif re.search('!',sentence):
        return 'Whoa, chill out!'    
    else:
        return 'Sure.'
