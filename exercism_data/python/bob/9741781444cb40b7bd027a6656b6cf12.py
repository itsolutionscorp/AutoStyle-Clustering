def hey(msg):
    if msg.isupper():
        return 'Whoa, chill out!'
    elif msg[len(msg) - 1] == '?':
        return 'Sure.'
    elif msg.isspace():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
