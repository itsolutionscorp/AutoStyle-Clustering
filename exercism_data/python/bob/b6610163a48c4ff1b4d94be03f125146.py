def hey(msg):
    if _isblank(msg):
        return 'Fine. Be that way!'
    elif _isshout(msg):
        return 'Woah, chill out!'
    elif _isquestion(msg):
        return 'Sure.'
    else:
        return 'Whatever.'


def _isblank(msg):
    return msg == '' or msg.isspace()


def _isshout(msg):
    return msg.isupper()


def _isquestion(msg):
    return msg.endswith('?')
