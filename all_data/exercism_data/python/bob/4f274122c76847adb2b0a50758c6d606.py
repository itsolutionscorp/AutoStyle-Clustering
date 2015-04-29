def hey(msg):
    if msg == msg.upper() and msg != msg.lower():
        return 'Whoa, chill out!'
    if msg.endswith('?'):
        return 'Sure.'
    if not msg.strip():
        return 'Fine. Be that way!'
    return 'Whatever.'
