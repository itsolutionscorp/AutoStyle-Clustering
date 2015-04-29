def hey(msg):
    if msg.isupper():
        return 'Woah, chill out!'
    elif msg[-1:] == '?':
        return 'Sure.'
    elif msg.strip() == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
