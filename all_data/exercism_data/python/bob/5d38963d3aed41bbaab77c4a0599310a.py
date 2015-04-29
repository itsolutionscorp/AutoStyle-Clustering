def hey(address):
    if not address or address.isspace():
        return 'Fine. Be that way!'
    elif address.isupper():
        return 'Whoa, chill out!'
    elif address.endswith('?') :
        return 'Sure.'
    else:
        return 'Whatever.'
