def hey(msg):
    '''Solution for the Python bob exercise at exercism.io'''
    msg = msg.strip()
    if not msg:
        return 'Fine. Be that way!'
    elif msg.isupper():
        return 'Woah, chill out!'
    elif msg.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
