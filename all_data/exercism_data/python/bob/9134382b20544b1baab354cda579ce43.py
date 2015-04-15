def hey(msg):
    if len(msg.strip()) == 0:
        return u'Fine. Be that way!'
    elif any(ch.isalpha() for ch in msg) and msg.upper() == msg:
        return u'Woah, chill out!'
    elif msg.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
