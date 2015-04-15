def hey(msg):
    if '' == msg or msg.isspace():
        return 'Fine. Be that way!'
    elif msg.isupper():
        return 'Whoa, chill out!'
    elif msg[-1] =='?':
        return 'Sure.'
    else:
        return 'Whatever.'
