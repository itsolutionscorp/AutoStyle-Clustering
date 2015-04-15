def hey(msg):
    '''Solution for the Python bob exercise at exercism.io'''
    msg = msg.strip()
    if not msg:
        return 'Fine. Be that way!'
    if msg.isupper():
        return 'Woah, chill out!'
    if msg.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
