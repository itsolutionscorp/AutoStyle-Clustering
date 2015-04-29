def hey(msg):
    if is_silence(msg):
        return 'Fine. Be that way!'
    elif is_yell(msg):
        return 'Woah, chill out!'
    elif is_question(msg):
        return 'Sure.'
    return 'Whatever.'

def is_question(msg):
    return msg.endswith('?')

def is_yell(msg):
    return msg.isupper()

def is_silence(msg):
    return not msg.strip()
