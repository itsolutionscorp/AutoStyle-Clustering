import re

def hey(speech):
    # Shouting - everything is in upper case.
    if speech.isupper():
        return 'Whoa, chill out!'
    # Questions - go by ? only
    elif speech.strip().endswith('?'):
        return 'Sure.'
    # Unless there is one letter or number, 
    elif not re.search(r'.*[\w]+.*', speech, re.U):
        return 'Fine. Be that way!'
    return 'Whatever.'
