def hey(thing):
    if silence(thing):
        return 'Fine. Be that way!'
    elif shouting(thing):
        return 'Whoa, chill out!'
    elif question(thing):
        return 'Sure.'
    else:
        return 'Whatever.'

def silence(thing):
    return thing.strip()==''

def shouting(thing):
    return thing.isupper()
    
def question(thing):
    return thing.endswith('?')
