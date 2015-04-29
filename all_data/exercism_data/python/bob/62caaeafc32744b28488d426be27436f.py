def hey(msg):
    if msg.isupper():
        return 'Whoa, chill out!'
    elif msg.endswith('?'):
        return 'Sure.'   
    elif len(msg.strip()) == 0:
        return 'Fine. Be that way!'    
    else:
        return 'Whatever.'
