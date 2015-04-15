def hey(msg):
    if '?' in msg[-3:] and not msg.isupper():
        return 'Sure.'
    elif msg.isupper():
        return 'Woah, chill out!'
    elif msg.isspace() or msg == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
