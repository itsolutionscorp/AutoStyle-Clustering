import string

def hey(greeting):
    if len(greeting.strip()) == 0:
        return 'Fine. Be that way!'
    elif all(not c.islower() for c in greeting) and any(c.isupper() for c in greeting):
        return 'Whoa, chill out!'
    elif greeting[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
