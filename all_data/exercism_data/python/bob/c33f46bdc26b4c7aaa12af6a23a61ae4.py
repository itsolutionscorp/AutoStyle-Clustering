def hey(msg):
    if silence(msg):
        return 'Fine. Be that way!'
    elif shouted_at(msg):
        return 'Woah, chill out!'
    elif got_asked(msg):
        return 'Sure.'
    return 'Whatever.'

def got_asked(msg):
    return msg.endswith('?')

def shouted_at(msg):
    return msg.isupper()

def silence(msg):
    return not msg.strip()
