def hey(msg):
    msg = msg.strip()
    if not msg:
        return 'Fine. Be that way!'
    if msg.isupper():
        return 'Whoa, chill out!'
    if msg.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
