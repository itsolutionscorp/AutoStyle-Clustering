import re

def hey(speech):
    # Shouting - everything is in upper case.
    if speech.isupper():
        return 'Whoa, chill out!'
    # Questions - go by ? only
    elif speech.endswith('?'):
        return 'Sure.'
    # Unless there is one letter or number, 
    elif re.match(r'.*[\w]+.*', speech, re.U) is None:
        return 'Fine. Be that way!'
    return 'Whatever.'
