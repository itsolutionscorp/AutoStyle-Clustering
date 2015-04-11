def hey(msg):
    if _is_silence(msg):
        return 'Fine. Be that way!'
    elif _is_yell(msg):
        return 'Woah, chill out!'
    elif _is_question(msg):
        return 'Sure.'
    return 'Whatever.'

def _is_question(msg):
    return msg.endswith('?')

def _is_yell(msg):
    return msg.isupper()

def _is_silence(msg):
    return not msg.strip()
